package com.example.dara

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.dara.R

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<View>(R.id.btnback_cal)

        btnBack.setOnClickListener {
            (activity as MainActivity).openTab(ShowFragment(),0)
        }
    }
}