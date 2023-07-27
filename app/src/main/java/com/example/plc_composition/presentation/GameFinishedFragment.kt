package com.example.plc_composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plc_composition.R
import com.example.plc_composition.databinding.FragmentGameFinishedBinding
import java.lang.RuntimeException


class GameFinishedFragment : Fragment() {

    private var _viewBinding: FragmentGameFinishedBinding? = null
    private val viewBinding: FragmentGameFinishedBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

}