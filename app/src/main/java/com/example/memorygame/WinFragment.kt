package com.example.memorygame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.memorygame.databinding.FragmentWinBinding

class WinFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWinBinding.inflate(inflater)

        binding.button2.setOnClickListener{
            this.findNavController().navigate(R.id.titleFragment)
        }

        return binding.root
    }

}