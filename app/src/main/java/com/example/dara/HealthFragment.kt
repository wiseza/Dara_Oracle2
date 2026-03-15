package com.example.dara

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class HealthFragment : Fragment(R.layout.fragment_health) {

    private var currentCardIndex: Int = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardImage = view.findViewById<ImageView>(R.id.img_card)
        val textAdvice = view.findViewById<TextView>(R.id.textAdvice)
        val btnOra = view.findViewById<Button>(R.id.btn_Ora)
        val btnb = view.findViewById<Button>(R.id.btn_back)
        val btnnx = view.findViewById<Button>(R.id.btn_next)

        val day = arguments?.getString("day") ?: ""
        val zodiac = arguments?.getString("zodiac") ?: ""

        currentCardIndex = UserPrefs.getHealthCardIndex(requireContext())
        if (currentCardIndex != -1) {
            val card = TarotDeck.cards[currentCardIndex]
            cardImage.setImageResource(card.image)
            textAdvice.text = card.meanings["health"] ?: ""
            btnOra.isEnabled = false
        } else {
            cardImage.setImageResource(R.drawable.backcard)
            textAdvice.text = ""
            btnOra.isEnabled = true
        }

        btnOra.setOnClickListener {
            if (UserPrefs.getHealthCardIndex(requireContext()) == -1) {
                val index = FortuneCalculator.getHealthCard(day, zodiac)
                val card = TarotDeck.cards[index]
                currentCardIndex = index

                UserPrefs.saveCardIndices(
                    requireContext(),
                    UserPrefs.getWorkCardIndex(requireContext()),
                    UserPrefs.getLoveCardIndex(requireContext()),
                    UserPrefs.getMoneyCardIndex(requireContext()),
                    index // health
                )

                flipCard(cardImage, card.image, 4, textAdvice, card.meanings["health"] ?: "")
                btnOra.isEnabled = false
            } else {
                Toast.makeText(requireContext(), "คุณได้ทำนายดวงสุขภาพวันนี้ไปแล้ว", Toast.LENGTH_SHORT).show()
            }
        }

        btnb.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, CategoriesFragment())
                .addToBackStack(null)
                .commit()
        }

        btnnx.setOnClickListener {
            val fragment = ShowFragment()
            fragment.arguments = arguments
            (activity as MainActivity).openTab(fragment, 2)
        }
    }

    private fun flipCard(imageView: ImageView, finalImage: Int, flips: Int, textView: TextView, advice: String) {
        if (flips <= 0) {
            imageView.setImageResource(finalImage)
            imageView.rotationY = 0f
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