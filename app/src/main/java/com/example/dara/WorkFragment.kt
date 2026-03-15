package com.example.dara

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.os.Handler
import android.os.Looper
import android.widget.Toast

class WorkFragment : Fragment(R.layout.fragment_work) {

    var currentCard: TarotCard? = null
    var showingInfo = false
    var currentWorkIndex: Int = 0
    private var currentCardIndex: Int = -1
    private lateinit var cardImage: ImageView
    private lateinit var textAdvice: TextView
    private lateinit var btnOra: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardImage = view.findViewById<ImageView>(R.id.img_card)
        val textAdvice = view.findViewById<TextView>(R.id.textAdvice)
        val btnOra = view.findViewById<Button>(R.id.btn_Ora)
        val btnb = view.findViewById<Button>(R.id.btn_back)
        val btnnx = view.findViewById<Button>(R.id.btn_next)

        val day = arguments?.getString("day") ?: ""
        val zodiac = arguments?.getString("zodiac") ?: ""

        if (UserPrefs.isTodayFortuneSaved(requireContext())) {
            currentCardIndex = UserPrefs.getWorkCardIndex(requireContext())
            if (currentCardIndex != -1) {
                val card = TarotDeck.cards[currentCardIndex]
                cardImage.setImageResource(card.image)
                textAdvice.text = card.meanings["work"] ?: ""
                btnOra.isEnabled = false
            }
        } else {
            cardImage.setImageResource(R.drawable.backcard)
            textAdvice.text = ""
        }

        btnOra.setOnClickListener {
            if (!UserPrefs.isTodayFortuneSaved(requireContext())) {
                // สุ่มการ์ดเฉพาะถ้ายังไม่ได้สุ่มในวันนี้
                val index = FortuneCalculator.getWorkCard(day, zodiac)
                val card = TarotDeck.cards[index]
                currentCardIndex = index

                // บันทึกการ์ดลง SharedPreferences (รวมทุกหมวด)
                // ควรบันทึกทุกหมวดพร้อมกันที่ ShowFragment หรือเก็บทีละตัว?
                // วิธีง่าย: บันทึกทีละตัวเมื่อสุ่มครบทุกหมวด หรือให้ ShowFragment เป็นตัวบันทึกสุดท้าย
                // แต่เพื่อให้การ์ดคงอยู่แม้ยังไม่ถึง ShowFragment เราสามารถบันทึกทีละหมวดได้
                saveSingleCardIndex(requireContext(), index, "work")

                flipCard(cardImage, card.image, 4, textAdvice, card.meanings["work"] ?: "")
                btnOra.isEnabled = false
            } else {
                Toast.makeText(requireContext(), "คุณได้ทำนายดวงวันนี้ไปแล้ว", Toast.LENGTH_SHORT).show()
            }
        }

        btnb.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, CategoriesFragment())
                .addToBackStack(null)
                .commit()
        }

        btnnx.setOnClickListener {
            val fragment = LoveFragment()
            val bundle = Bundle().apply {
                putAll(arguments ?: Bundle())
                putInt("workIndex", currentWorkIndex)
            }
            fragment.arguments = bundle
            (activity as MainActivity).openTab(fragment, 2)
        }
    }
    private fun saveSingleCardIndex(context: Context, index: Int, category: String) {
        val work = if (category == "work") index else UserPrefs.getWorkCardIndex(context)
        val love = if (category == "love") index else UserPrefs.getLoveCardIndex(context)
        val money = if (category == "money") index else UserPrefs.getMoneyCardIndex(context)
        val health = if (category == "health") index else UserPrefs.getHealthCardIndex(context)
        UserPrefs.saveCardIndices(context, work, love, money, health)
    }


    fun flipCard(
        imageView: ImageView,
        finalImage: Int,
        flips: Int,
        textView: TextView,
        advice: String
    ) {
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
