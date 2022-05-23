package ru.alex009.moko.network.myapplication

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import kotlinx.coroutines.launch

class JokeViewModel : ViewModel() {
    private val _jokeText: MutableLiveData<String> = MutableLiveData("")
    val jokeText: LiveData<String> get() = _jokeText

    private val httpClient: HttpClient = HttpClient {
        install(Logging)
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    fun onRandomJokePressed() {
        viewModelScope.launch {
            val joke: Result<Joke> = runCatching {
                httpClient.get("https://api.chucknorris.io/jokes/random")
            }
            joke.onSuccess { println("joke: $it") }
                .onSuccess { _jokeText.value = it.value }
                .onFailure { println("error: $it") }
        }
    }
}
