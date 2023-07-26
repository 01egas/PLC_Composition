package com.example.plc_composition.domain.repository

import com.example.plc_composition.domain.entity.GameSettings
import com.example.plc_composition.domain.entity.Level
import com.example.plc_composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(
        maxValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level): GameSettings

}