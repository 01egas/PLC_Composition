package com.example.plc_composition.presentation

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.plc_composition.data.GameRepositoryImpl
import com.example.plc_composition.domain.entity.GameResult
import com.example.plc_composition.domain.entity.GameSettings
import com.example.plc_composition.domain.entity.Level
import com.example.plc_composition.domain.entity.Question
import com.example.plc_composition.domain.usecases.GenerateQuestionUseCase
import com.example.plc_composition.domain.usecases.GetGameSettingsUseCase

class GameFragmentViewModel : ViewModel() {
    private val gameRepository = GameRepositoryImpl
    private val generateQuestionUseCase = GenerateQuestionUseCase(gameRepository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(gameRepository)

    private var rightAnswersCounter = 0
    private var questionCounter = 0

    private val _timeCounter = MutableLiveData<Long>()
    val timeCounter: LiveData<Long>
        get() = _timeCounter

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _rightAnswers = MutableLiveData<Int>()
    val rightAnswers: LiveData<Int>
        get() = _rightAnswers

    private val _level = MutableLiveData<Level>()
    private val _gameSettings = MutableLiveData<GameSettings>()
    val gameSettings: LiveData<GameSettings>
        get() = _gameSettings

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult


    fun startGame(level: Level) {
        _level.value = level
        getGameSettings(level)
        setTimeCounter()
        generateQuestion()
    }

    private fun getGameSettings(level: Level) {
        _gameSettings.value = getGameSettingsUseCase(level)
    }

    private fun generateQuestion() {
        _rightAnswers.value = rightAnswersCounter
        _gameSettings.value?.let {
            _question.value = generateQuestionUseCase(it.maxSumValue)
        }
    }

    private fun endGame() {
        getGameResult()
    }

    private fun setTimeCounter() {
        _gameSettings.value?.let { gameSett ->
            object : CountDownTimer(
                gameSett.gameTimeInSeconds.toLong() * 1000,
                1000) {
                override fun onTick(millisUntilFinished: Long) {
                    _timeCounter.value = millisUntilFinished / 1000
                }

                override fun onFinish() {
                    endGame()
                }
            }.start()
        }
    }

    fun checkAnswer(answer: Int) {
        questionCounter++
        question.value?.let {
            if (it.sum == it.visibleNumber + answer) {
                rightAnswersCounter++
                _rightAnswers.value = rightAnswersCounter
            }
        }
        generateQuestion()
    }

    private fun getGameResult() {
        var winner = false
        _gameSettings.value?.let { gameSettings ->
            if (rightAnswersCounter > gameSettings.minCountOfRightAnswers
                && ((rightAnswersCounter * 100 / questionCounter) > gameSettings.minPercentOfRightAnswers)
            ) {
                winner = true
            }
            _gameResult.value =
                GameResult(winner, rightAnswersCounter, questionCounter, gameSettings)
        }
    }
}