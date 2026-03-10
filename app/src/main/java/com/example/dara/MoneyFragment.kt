package com.example.dara

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class MoneyFragment : Fragment(R.layout.fragment_money) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardImage = view.findViewById<ImageView>(R.id.img_card)
        val textAdvice = view.findViewById<TextView>(R.id.textAdvice)
        val btnOra = view.findViewById<Button>(R.id.btn_Ora)
        val btnb = view.findViewById<Button>(R.id.btn_back)
        val btnnx = view.findViewById<Button>(R.id.btn_next)

        val cards = listOf(
            Pair(R.drawable.empress, "วันนี้เหมาะกับการดูแลตัวเอง และเปิดใจรับสิ่งดี ๆ"),
            Pair(R.drawable.fool, "ลองเริ่มต้นสิ่งใหม่ อย่ากลัวการเปลี่ยนแปลง"),
            Pair(R.drawable.magician, "คุณมีศักยภาพที่จะทำสิ่งที่คิดให้สำเร็จ"),
            Pair(R.drawable.highp, "ใช้สัญชาตญาณของคุณ มันกำลังนำทางคุณอยู่")
        )

        btnOra.setOnClickListener {

            val randomCard = cards[Random.nextInt(cards.size)]
            cardImage.setImageResource(randomCard.first)
            textAdvice.text = randomCard.second
        }

        btnb.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, CategoriesFragment())
                .addToBackStack(null)
                .commit()
        }

        btnnx.setOnClickListener {
            (activity as MainActivity).openTab(HealthFragment(),2)
        }
    }
}