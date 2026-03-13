package com.example.dara

import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment

class ModeFragment : Fragment(R.layout.fragment_mode) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ปุ่ม X
        val btnXmode = view.findViewById<View>(R.id.btn_x_mode)

        btnXmode.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, HomeFragment())
                .addToBackStack(null)
                .commit()
        }

        // ปุ่มย้อนกลับ
        val btnModedown = view.findViewById<View>(R.id.btn_modedown)

        btnModedown?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        // Switch
        val switchTheme = view.findViewById<Switch>(R.id.switchdark)

        val pref = requireActivity().getSharedPreferences("settings", 0)
        val editor = pref.edit()

        // โหลดค่าที่เคยเลือก
        val isDark = pref.getBoolean("darkMode", false)

        switchTheme.isChecked = isDark

        // เมื่อกด switch
        switchTheme.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )

                editor.putBoolean("darkMode", true).apply()

            } else {

                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )

                editor.putBoolean("darkMode", false).apply()
            }

            // restart activity เพื่อให้ theme เปลี่ยน
            requireActivity().recreate()
        }
    }
}