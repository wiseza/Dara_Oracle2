package com.example.dara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.dara.R

class SettingFragment : Fragment(R.layout.fragment_setting) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnx = view.findViewById<View>(R.id.btn_x)

        btnx.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, HomeFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}