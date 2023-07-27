package com.example.plc_composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plc_composition.R
import com.example.plc_composition.databinding.FragmentChooseLevelBinding
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

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

}