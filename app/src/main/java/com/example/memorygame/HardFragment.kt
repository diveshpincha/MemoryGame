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
import com.example.memorygame.databinding.FragmentHardBinding

class HardFragment : Fragment() {

    private lateinit var buttons : List<ImageButton>

    private lateinit var cards : List<MemoryCard>

    private var selectedCard : Int? = null

    private var matches = 0

    var moves = MutableLiveData<Int>()

    private lateinit var binding : FragmentHardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        matches= 0
        moves.value=32

        binding = FragmentHardBinding.inflate(inflater)
        binding.moves=this

        val images = mutableListOf(
            R.drawable.ic_baseline_bike_scooter_24,
            R.drawable.ic_baseline_sentiment_very_satisfied_24,
            R.drawable.ic_baseline_weekend_24,
            R.drawable.ic_baseline_wine_bar_24,
            R.drawable.ic_baseline_tips_and_updates_24,
            R.drawable.ic_baseline_watch_later_24,
            R.drawable.ic_baseline_thumb_up_24,
            R.drawable.ic_baseline_task_24,
    R.drawable.ic_baseline_warning_24,
    R.drawable.ic_baseline_water_drop_24,
        )

        images.addAll(images)
        images.shuffle()

        buttons= listOf(binding.imageButton1 ,binding.imageButton2 ,binding.imageButton3,binding.imageButton4,
            binding.imageButton5,binding.imageButton6,binding.imageButton7,binding.imageButton8,
            binding.imageButton9,binding.imageButton10,binding.imageButton11,binding.imageButton12
            ,binding.imageButton13,binding.imageButton14,binding.imageButton15,binding.imageButton16
            ,binding.imageButton17,binding.imageButton18,binding.imageButton19,binding.imageButton20)



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
            button.setImageResource(if (card.face_up) card.identity else R.drawable.ic_android_black_24dp)
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
            Toast.makeText(this.context,"MATCH FOUND", Toast.LENGTH_SHORT).show()
            matches++
            cards[past].matched=true
            cards[present].matched=true
        }
    }
}