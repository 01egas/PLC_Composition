package com.example.plc_composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plc_composition.R
import com.example.plc_composition.databinding.FragmentGameBinding
import com.example.plc_composition.domain.entity.GameResult
import com.example.plc_composition.domain.entity.GameSettings
import com.example.plc_composition.domain.entity.Level
import java.lang.RuntimeException


class GameFragment : Fragment() {

    private var _viewBinding: FragmentGameBinding? = null
    private val viewBinding: FragmentGameBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentGameBinding == null")
    private lateinit var level: Level


    companion object{
        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level): GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL, level)
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
        setButtonListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun parseArgs(){
        level = requireArguments().getSerializable(KEY_LEVEL) as Level
    }

    private fun setButtonListener(){
        with(viewBinding) {
            tvOption1.setOnClickListener{
                launchGameFinishFragment(GameResult(true, 1, 1, GameSettings(3, 3,4,5)))
            }
        }
    }

    private fun launchGameFinishFragment(gameResult: GameResult){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

}