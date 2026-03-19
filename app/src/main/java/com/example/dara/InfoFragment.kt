package com.example.dara

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class InfoFragment : Fragment(R.layout.fragment_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        val localeThai = Locale("th", "TH")

        val tvDayName = view.findViewById<TextView>(R.id.tv_day_name_info)
        tvDayName?.text = SimpleDateFormat("EEEE", localeThai).format(calendar.time)


        val tvFullDate = view.findViewById<TextView>(R.id.tv_full_date_info)
        val dayMonth = SimpleDateFormat("dd MMMM", localeThai).format(calendar.time)
        val thaiYear = calendar.get(Calendar.YEAR) + 543
        tvFullDate?.text = "$dayMonth $thaiYear"

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

            if (day == "เลือกวัน" || zodiac == "เลือกราศี") {
                Toast.makeText(requireContext(), "กรุณาเลือกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            UserPrefs.saveUserInfo(requireContext(), day, zodiac)

            (activity as MainActivity).openTab(CategoriesFragment(), 2)
        }
    }
}