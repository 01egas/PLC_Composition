package com.example.plc_composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.plc_composition.R
import com.example.plc_composition.databinding.FragmentChooseLevelBinding
import com.example.plc_composition.domain.entity.Level
import java.lang.RuntimeException

class ChooseLevelFragment : Fragment() {

    private var _viewBinding: FragmentChooseLevelBinding? = null
    private val viewBinding: FragmentChooseLevelBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentChooseLevelBinding == null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentChooseLevelBinding.inflate(inflater, container, false)
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

    private fun launchGameFragment(level: Level){
        findNavController().navigate(ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level))
    }

    private fun setButtonListener(){
        with(viewBinding) {
            buttonLevelTest.setOnClickListener{
                launchGameFragment(Level.TEST)
            }
            buttonLevelEasy.setOnClickListener{
                launchGameFragment(Level.EASY)
            }
            buttonLevelNormal.setOnClickListener{
                launchGameFragment(Level.NORMAL)
            }
            buttonLevelHard.setOnClickListener{
                launchGameFragment(Level.HARD)
            }
        }
    }

}