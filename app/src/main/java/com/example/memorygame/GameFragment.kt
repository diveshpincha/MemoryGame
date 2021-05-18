package com.example.memorygame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.memorygame.R.drawable.*
import com.example.memorygame.databinding.GameFragmentBinding

class GameFragment : Fragment() {

    private lateinit var buttons : List<ImageButton>

    private lateinit var cards : List<MemoryCard>

    private var selectedCard : Int? = null

    private var matches = 0

     var moves = MutableLiveData<Int>()

    private lateinit var binding : GameFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        matches= 0
        moves.value=7

        binding = GameFragmentBinding.inflate(inflater)
        binding.moves=this

        val images = mutableListOf(ic_baseline_bike_scooter_24,
            ic_baseline_sentiment_very_satisfied_24, ic_baseline_weekend_24, ic_baseline_wine_bar_24)

        images.addAll(images)
        images.shuffle()

        buttons= listOf(binding.imageButton1 ,binding.imageButton2 ,binding.imageButton3,binding.imageButton4,
            binding.imageButton5,binding.imageButton6,binding.imageButton7,binding.imageButton8 ) as List<ImageButton>



        cards = buttons.indices.map{
            i -> MemoryCard(images[i])
        }

        buttons.forEachIndexed{index, button -> button.setOnClickListener {
            updateCard(index)
            updateView()

        }  }
        binding.lifecycleOwner=this

        return binding.root
    }


    fun updateView(){
        cards.forEachIndexed { index, card ->
            val button=buttons[index]
            if(card.matched){
                button.alpha=0.25F
            }
            button.setImageResource(if (card.face_up) card.identity else ic_android_black_24dp)
        }
    }

    fun updateCard(index : Int){
        val card = cards[index]
        if (card.face_up) {
            Toast.makeText(this.context, "Invalid move!", Toast.LENGTH_SHORT).show()
            return
        }
        if (selectedCard==null){
            reset()
            selectedCard=index
        }else{
            checkMatch(selectedCard!!,index)
            moves.value=moves.value?.minus(1)

            if(matches==10){
                this.findNavController().navigate(R.id.winFragment)
            }
            else if (moves.value!! == 0){
                this.findNavController().navigate(R.id.loseFragment)
            }
            selectedCard=null
        }
        card.face_up = !card.face_up
    }

    private fun reset() {
        for(card in cards){
            if(!card.matched){
                card.face_up=false
            }
            updateView()
        }
    }

    private fun checkMatch(past:Int,present:Int) {
        if (cards[past].identity==cards[present].identity){
            Toast.makeText(this.context,"MATCH FOUND",Toast.LENGTH_SHORT).show()
            matches++
            cards[past].matched=true
            cards[present].matched=true
        }
    }

}