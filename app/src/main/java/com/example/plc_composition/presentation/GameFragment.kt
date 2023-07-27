package com.example.plc_composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plc_composition.R
import com.example.plc_composition.databinding.FragmentGameBinding
import java.lang.RuntimeException


class GameFragment : Fragment() {

    private var _viewBinding: FragmentGameBinding? = null
    private val viewBinding: FragmentGameBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentGameBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

}