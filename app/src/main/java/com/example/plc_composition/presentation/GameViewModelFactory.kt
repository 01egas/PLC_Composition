package com.example.plc_composition.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.plc_composition.domain.entity.Level
import ru.sumin.composition.presentation.GameViewModel

class GameViewModelFactory(
    private val application: Application,
    private val level: Level
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(application, level) as T
        }
        throw RuntimeException("Unknown viewModel class $modelClass")
    }
}