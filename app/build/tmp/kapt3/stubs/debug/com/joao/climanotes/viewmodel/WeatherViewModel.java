package com.joao.climanotes.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0010"}, d2 = {"Lcom/joao/climanotes/viewmodel/WeatherViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/joao/climanotes/repository/WeatherRepository;", "(Lcom/joao/climanotes/repository/WeatherRepository;)V", "_weather", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/joao/climanotes/network/WeatherResponse;", "weather", "Lkotlinx/coroutines/flow/StateFlow;", "getWeather", "()Lkotlinx/coroutines/flow/StateFlow;", "loadWeather", "", "city", "", "app_debug"})
public final class WeatherViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.joao.climanotes.repository.WeatherRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.joao.climanotes.network.WeatherResponse> _weather = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.joao.climanotes.network.WeatherResponse> weather = null;
    
    public WeatherViewModel(@org.jetbrains.annotations.NotNull()
    com.joao.climanotes.repository.WeatherRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.joao.climanotes.network.WeatherResponse> getWeather() {
        return null;
    }
    
    public final void loadWeather(@org.jetbrains.annotations.NotNull()
    java.lang.String city) {
    }
}