package com.example.memorygame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.memorygame.databinding.FragmentLoseBinding


class LoseFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoseBinding.inflate(inflater)

        binding.button2.setOnClickListener{
            this.findNavController().navigateUp()
        }

        return binding.root
    }

}