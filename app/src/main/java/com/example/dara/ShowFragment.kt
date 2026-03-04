package com.example.dara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView // หรือเปลี่ยนเป็น ImageButton ตามประเภทใน XML
import androidx.fragment.app.Fragment

class ShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_show, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext = view.findViewById<View>(R.id.btn_next)

        btnNext.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.contentContainer, CalendarFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}