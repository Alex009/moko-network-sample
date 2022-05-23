package ru.alex009.moko.network.myapplication

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.network.generated.apis.JokesApi
import dev.icerock.moko.network.generated.models.Joke
import io.ktor.client.HttpClient
import io.ktor.client.features.logging.Logging
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class JokeViewModel : ViewModel() {
    private val _jokeText: MutableLiveData<String> = MutableLiveData("")
    val jokeText: LiveData<String> get() = _jokeText

    private val httpClient: HttpClient = HttpClient {
        install(Logging)
    }
    private val jokesApi: JokesApi = JokesApi(
        json = Json.Default,
        httpClient = httpClient
    )

    fun onRandomJokePressed() {
        viewModelScope.launch {
            val joke: Result<Joke> = runCatching { jokesApi.jokesRandomGet() }
            joke.onSuccess { println("joke: $it") }
                .onSuccess { _jokeText.value = it.value }
                .onFailure { println("error: $it") }
        }
    }
}
