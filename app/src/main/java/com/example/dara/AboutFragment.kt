package com.example.dara

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class AboutFragment : Fragment(R.layout.fragment_about) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBack = view.findViewById<View>(R.id.ab_btn_back)

        btnBack?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}