package com.joao.climanotes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joao.climanotes.model.Category

@Composable
fun CategoryScreen(
    categories: List<Category>,
    onAddCategory: (String, String) -> Unit = { _, _ -> },
    onDeleteCategory: (Category) -> Unit = {},
    onEditCategory: (Category, String, String) -> Unit = { _, _, _ -> },
    onCategoryClick: (Category) -> Unit = {}
) {
    var newName by remember { mutableStateOf("") }
    var newDescription by remember { mutableStateOf("") }

    var editingCategory by remember { mutableStateOf<Category?>(null) }
    var editedName by remember { mutableStateOf("") }
    var editedDescription by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Título estilizado
        Text(
            text = "Categorias",
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Nome
        OutlinedTextField(
            value = newName,
            onValueChange = { newName = it },
            label = { Text("Nome da Categoria") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo Descrição
        OutlinedTextField(
            value = newDescription,
            onValueChange = { newDescription = it },
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botão Adicionar estilizado
        Button(
            onClick = {
                if (newName.isNotBlank()) {
                    onAddCategory(newName, newDescription)
                    newName = ""
                    newDescription = ""
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6A4CC2)
            )
        ) {
            Text("Adicionar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // LISTA EM CARDS BONITOS
        LazyColumn {
            items(categories) { category ->
                CategoryCard(
                    category = category,
                    onEdit = {
                        editingCategory = category
                        editedName = category.name
                        editedDescription = category.description
                    },
                    onDelete = { onDeleteCategory(category) },
                    onClick = { onCategoryClick(category) }
                )
            }
        }
    }

    // DIALOG EDITAR
    if (editingCategory != null) {
        AlertDialog(
            onDismissRequest = { editingCategory = null },
            title = { Text("Editar Categoria") },
            text = {
                Column {
                    OutlinedTextField(
                        value = editedName,
                        onValueChange = { editedName = it },
                        label = { Text("Nome") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = editedDescription,
                        onValueChange = { editedDescription = it },
                        label = { Text("Descrição") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        editingCategory?.let {
                            onEditCategory(it, editedName, editedDescription)
                        }
                        editingCategory = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A4CC2)
                    )
                ) {
                    Text("Salvar", color = Color.White)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { editingCategory = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun CategoryCard(
    category: Category,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5EFF9)
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = category.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = category.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {

                // Botão Editar
                Button(
                    onClick = onEdit,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A4CC2)
                    ),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text("Editar", color = Color.White)
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Botão Excluir
                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFC62828)
                    ),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text("Excluir", color = Color.White)
                }
            }
        }
    }
}

