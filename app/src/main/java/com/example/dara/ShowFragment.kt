package com.example.dara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class ShowFragment : Fragment(R.layout.fragment_show) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext = view.findViewById<ImageView>(R.id.btnshow_next)

        btnNext.setOnClickListener {
            (activity as MainActivity).openTab(CalendarFragment(),1)
        }
    }
}
