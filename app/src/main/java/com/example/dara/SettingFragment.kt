package com.example.dara

import android.os.Bundle
import android.view.View
import android.widget.ImageView // เพิ่มตัวนี้ถ้าปุ่มเป็นรูปภาพ
import androidx.fragment.app.Fragment

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

        val btnAbout = view.findViewById<View>(R.id.btn_about)
        btnAbout.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, AboutFragment()) // ตรวจสอบชื่อ Class ว่าเป็น AboutFragment หรือไม่
                .addToBackStack(null)
                .commit()
        }
        val btnMode = view.findViewById<View>(R.id.btn_mode)
        btnMode.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, ModeFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}