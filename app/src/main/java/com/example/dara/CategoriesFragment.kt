package com.example.dara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.dara.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        val localeThai = Locale("th", "TH")

        val tvDayName = view.findViewById<TextView>(R.id.tv_day_name_cat)
        tvDayName?.text = SimpleDateFormat("EEEE", localeThai).format(calendar.time)

        // 2. แสดงวันที่ เดือน ปี พ.ศ.
        val tvFullDate = view.findViewById<TextView>(R.id.tv_full_date_cat)
        val dayMonth = SimpleDateFormat("dd MMMM", localeThai).format(calendar.time)
        val thaiYear = calendar.get(Calendar.YEAR) + 543
        tvFullDate?.text = "$dayMonth $thaiYear"


        val btnop = view.findViewById<View>(R.id.btn_open)

        btnop.setOnClickListener {
            (activity as MainActivity).openTab(WorkFragment(),2)
        }
    }
}
