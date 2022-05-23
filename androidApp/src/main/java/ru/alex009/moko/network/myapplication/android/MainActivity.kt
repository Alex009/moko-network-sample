package ru.alex009.moko.network.myapplication.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.icerock.moko.mvvm.getViewModel
import dev.icerock.moko.mvvm.livedata.bindText
import ru.alex009.moko.network.myapplication.JokeViewModel
import ru.alex009.moko.network.myapplication.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: JokeViewModel = getViewModel { JokeViewModel() }

        binding.jokeText.bindText(this, viewModel.jokeText)

        binding.randomBtn.setOnClickListener { viewModel.onRandomJokePressed() }
    }
}
