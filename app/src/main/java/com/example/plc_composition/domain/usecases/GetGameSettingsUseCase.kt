package com.example.plc_composition.domain.usecases

import com.example.plc_composition.domain.entity.GameSettings
import com.example.plc_composition.domain.entity.Level
import com.example.plc_composition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
    ) {

    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }

}