package com.example.dara

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment

class InfoFragment : Fragment(R.layout.fragment_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerDay = view.findViewById<Spinner>(R.id.spinnerDay)
        val spinnerZodiac = view.findViewById<Spinner>(R.id.spinnerZodiac)
        val btnInfo = view.findViewById<Button>(R.id.btn_info)

        val dayAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.days_array,
            android.R.layout.simple_spinner_item
        )
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDay.adapter = dayAdapter

        val zodiacAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.zodiac_array,
            android.R.layout.simple_spinner_item
        )
        zodiacAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerZodiac.adapter = zodiacAdapter

        btnInfo.setOnClickListener {

            val day = spinnerDay.selectedItem.toString()
            val zodiac = spinnerZodiac.selectedItem.toString()

            if (day == "เลือกวัน") {
                Toast.makeText(requireContext(),"กรุณาเลือกวันเกิด",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (zodiac == "เลือกราศี") {
                Toast.makeText(requireContext(),"กรุณาเลือกราศี",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val bundle = Bundle()
            bundle.putString("day", day)
            bundle.putString("zodiac", zodiac)

            val fragment = CategoriesFragment()
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}