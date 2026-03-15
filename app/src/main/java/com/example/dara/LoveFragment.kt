package com.example.dara

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoveFragment : Fragment(R.layout.fragment_love) {

    var currentCard: TarotCard? = null
    var showingInfo = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardImage = view.findViewById<ImageView>(R.id.img_card)
        val textAdvice = view.findViewById<TextView>(R.id.textAdvice)
        val btnOra = view.findViewById<Button>(R.id.btn_Ora)
        val btnb = view.findViewById<Button>(R.id.btn_back)
        val btnnx = view.findViewById<Button>(R.id.btn_next)

        val day = arguments?.getString("day") ?: ""
        val zodiac = arguments?.getString("zodiac") ?: ""

        btnOra.setOnClickListener {

            val index = FortuneCalculator.getLoveCard(day, zodiac)
            val card = TarotDeck.cards[index]

            cardImage.setImageResource(R.drawable.backcard)
            cardImage.cameraDistance = 8000 * resources.displayMetrics.density

            flipCard(cardImage, card.image, 4, textAdvice, card.meanings["love"] ?: "")
        }


        btnb.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, CategoriesFragment())
                .addToBackStack(null)
                .commit()
        }

        btnnx.setOnClickListener {

            val fragment = MoneyFragment()
            fragment.arguments = arguments

            (activity as MainActivity).openTab(fragment, 2)
        }
    }
    fun flipCard(imageView: ImageView, finalImage: Int, flips: Int, textView: TextView, advice: String) {
        if (flips <= 0) {
            imageView.setImageResource(finalImage)
            imageView.rotationY = 0f

            // แสดงข้อความตอนหมุนเสร็จ
            textView.text = advice
            return
        }

        imageView.animate()
            .rotationY(90f)
            .setDuration(200)
            .withEndAction {

                if (flips == 1) {
                    imageView.setImageResource(finalImage)
                } else {
                    imageView.setImageResource(R.drawable.backcard)
                }

                imageView.rotationY = -90f

                imageView.animate()
                    .rotationY(0f)
                    .setDuration(200)
                    .withEndAction {
                        flipCard(imageView, finalImage, flips - 1, textView, advice)
                    }
                    .start()
            }
            .start()
    }
}