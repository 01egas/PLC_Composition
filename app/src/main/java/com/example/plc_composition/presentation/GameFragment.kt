package com.example.plc_composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.plc_composition.R
import com.example.plc_composition.databinding.FragmentGameBinding
import com.example.plc_composition.domain.entity.GameResult
import com.example.plc_composition.domain.entity.Level
import java.lang.RuntimeException


class GameFragment : Fragment() {

    private var _viewBinding: FragmentGameBinding? = null
    private val viewBinding: FragmentGameBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentGameBinding == null")
    private lateinit var level: Level
    private lateinit var viewModel: GameFragmentViewModel


    companion object{
        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level): GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentGameBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[GameFragmentViewModel::class.java]
        setButtonListener()
        launchGame()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun parseArgs(){
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    private fun setButtonListener(){
        with(viewBinding) {
            tvOption1.setOnClickListener{
                checkAnswer(tvOption1.text.toString().toInt())
            }
            tvOption2.setOnClickListener{
                checkAnswer(tvOption2.text.toString().toInt())
            }
            tvOption3.setOnClickListener{
                checkAnswer(tvOption3.text.toString().toInt())
            }
            tvOption4.setOnClickListener{
                checkAnswer(tvOption4.text.toString().toInt())
            }
            tvOption5.setOnClickListener{
                checkAnswer(tvOption5.text.toString().toInt())
            }
            tvOption6.setOnClickListener{
                checkAnswer(tvOption6.text.toString().toInt())
            }
        }
    }

    private fun launchGameFinishFragment(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    private fun launchGame() = with(viewBinding){
        viewModel.timeCounter.observe(viewLifecycleOwner){
            updateCounter(it.toString())
        }
        viewModel.rightAnswers.observe(viewLifecycleOwner){
            val formatString = String.format(getString(R.string.progress_answers),
                it.toString(),
                viewModel.gameSettings.value?.minCountOfRightAnswers)
            updateRightAnswers(formatString)
        }
        viewModel.gameResult.observe(viewLifecycleOwner){
            endGame(it)
        }
        viewModel.question.observe(viewLifecycleOwner){
            tvSum.text = it.sum.toString()
            tvLeftNumber.text = it.visibleNumber.toString()
            tvOption1.text = it.options[0].toString()
            tvOption2.text = it.options[1].toString()
            tvOption3.text = it.options[2].toString()
            tvOption4.text = it.options[3].toString()
            tvOption5.text = it.options[4].toString()
            tvOption6.text = it.options[5].toString()
        }
        viewModel.startGame(level)
    }

    private fun checkAnswer(answer: Int){
        viewModel.checkAnswer(answer)
    }

    private fun endGame(gameResult: GameResult){
        launchGameFinishFragment(gameResult)
    }

    private fun updateRightAnswers(answerString: String) = with(viewBinding){
        tvAnswersProgress.text = answerString
    }

    private fun updateCounter(counter: String) = with(viewBinding){
        tvTimer.text = counter
    }
}