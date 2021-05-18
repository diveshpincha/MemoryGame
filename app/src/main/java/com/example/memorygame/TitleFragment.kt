package com.example.memorygame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.memorygame.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentTitleBinding.inflate(inflater)

        binding.easy.setOnClickListener {
            it.findNavController().navigate(R.id.gameFragment)
        }

        binding.medium.setOnClickListener {
            it.findNavController().navigate(R.id.mediumFragment)
        }

        binding.hard.setOnClickListener {
            it.findNavController().navigate(R.id.hardFragment)
        }

        return binding.root
    }


}