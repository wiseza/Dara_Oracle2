package com.example.dara

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class ModeFragment : Fragment(R.layout.fragment_mode) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // อ้างอิงปุ่ม
        val btnXmode = view.findViewById<View>(R.id.btn_x_mode)

        btnXmode.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, HomeFragment())
                .addToBackStack(null)
                .commit()
        }
        val btnModedown = view.findViewById<View>(R.id.btn_modedown)

        btnModedown?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}