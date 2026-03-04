package com.example.dara

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<View>(R.id.btnback_cal)

        btnBack?.setOnClickListener {
            if (parentFragmentManager.backStackEntryCount > 0) {
                parentFragmentManager.popBackStack()
            } else {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.contentContainer, ShowFragment())
                    .commit()
            }
        }
    }
}