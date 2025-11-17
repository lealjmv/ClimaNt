package com.joao.climanotes

import androidx.compose.ui.unit.dp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joao.climanotes.database.DatabaseInstance
import com.joao.climanotes.model.Category
import com.joao.climanotes.model.Note
import com.joao.climanotes.repository.WeatherRepository
import com.joao.climanotes.viewmodel.WeatherViewModel
import com.joao.climanotes.viewmodel.WeatherViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = DatabaseInstance.getDatabase(this)
        val categoryDao = db.categoryDao()
        val noteDao = db.noteDao()

        val categoriesState = mutableStateListOf<Category>()
        val notesState = mutableStateListOf<Note>()

        var currentScreen by mutableStateOf("categories")
        var selectedCategory by mutableStateOf<Category?>(null)

        // ⭐ Weather API
        val weatherRepository = WeatherRepository()
        val weatherViewModelFactory = WeatherViewModelFactory(weatherRepository)

        // Carrega categorias iniciais
        lifecycleScope.launch {
            if (categoryDao.getAll().isEmpty()) {
                categoryDao.insert(Category(name = "Teste 1", description = "Descrição de teste"))
            }
            categoriesState.clear()
            categoriesState.addAll(categoryDao.getAll())
        }

        setContent {

            // ⭐ ViewModel do clima
            val weatherViewModel: WeatherViewModel = viewModel(
                factory = weatherViewModelFactory
            )

            val weatherState by weatherViewModel.weather.collectAsState()

            // Chama API quando a tela abre
            LaunchedEffect(Unit) {
                weatherViewModel.loadWeather("São Paulo")
            }

            // ⭐ Layout principal
            Column(modifier = Modifier.fillMaxSize()) {

                // ⭐ CLIMA NO TOPO, MAIOR E BONITO
                weatherState?.let { weather ->
                    Text(
                        text = "Clima agora: ${weather.main.temp}°C — ${weather.weather[0].description}",
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                // ⭐ TELAS PRINCIPAIS
                when (currentScreen) {

                    "categories" -> CategoryScreen(
                        categories = categoriesState,
                        onAddCategory = { name, description ->
                            lifecycleScope.launch {
                                categoryDao.insert(Category(name = name, description = description))
                                categoriesState.clear()
                                categoriesState.addAll(categoryDao.getAll())
                            }
                        },
                        onDeleteCategory = { category ->
                            lifecycleScope.launch {
                                categoryDao.delete(category)
                                categoriesState.clear()
                                categoriesState.addAll(categoryDao.getAll())
                            }
                        },
                        onEditCategory = { category, newName, newDescription ->
                            lifecycleScope.launch {
                                val updated = category.copy(name = newName, description = newDescription)
                                categoryDao.update(updated)
                                categoriesState.clear()
                                categoriesState.addAll(categoryDao.getAll())
                            }
                        },
                        onCategoryClick = { category ->
                            selectedCategory = category
                            lifecycleScope.launch {
                                notesState.clear()
                                notesState.addAll(noteDao.getNotesByCategory(category.id))
                            }
                            currentScreen = "notes"
                        }
                    )

                    "notes" -> NoteScreen(
                        notes = notesState,
                        onAddNote = { title, content ->
                            selectedCategory?.let { cat ->
                                lifecycleScope.launch {
                                    noteDao.insert(Note(title = title, content = content, categoryId = cat.id))
                                    notesState.clear()
                                    notesState.addAll(noteDao.getNotesByCategory(cat.id))
                                }
                            }
                        },
                        onDeleteNote = { note ->
                            lifecycleScope.launch {
                                noteDao.delete(note)
                                selectedCategory?.let { cat ->
                                    notesState.clear()
                                    notesState.addAll(noteDao.getNotesByCategory(cat.id))
                                }
                            }
                        },
                        onBack = { currentScreen = "categories" }
                    )
                }
            }
        }
    }
}
