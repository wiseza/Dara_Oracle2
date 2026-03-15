package com.example.dara

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()

        if (UserPrefs.hasUserInfo(context) && UserPrefs.isTodayFortuneSaved(context)) {

            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, ShowFragment())
                .commit()

        }

        val calendar = Calendar.getInstance()
        val localeThai = Locale("th", "TH")

        val tvDayName = view.findViewById<TextView>(R.id.tv_day_name_home)
        tvDayName?.text = SimpleDateFormat("EEEE", localeThai).format(calendar.time)

        // 2. แสดงวันที่ เดือน ปี พ.ศ.
        val tvFullDate = view.findViewById<TextView>(R.id.tv_full_date_home)
        val dayMonth = SimpleDateFormat("dd MMMM", localeThai).format(calendar.time)
        val thaiYear = calendar.get(Calendar.YEAR) + 543
        tvFullDate?.text = "$dayMonth $thaiYear"

        val btnMain = view.findViewById<Button>(R.id.btn_main)
        val btnset = view.findViewById<View>(R.id.btn_setting)

        btnMain.setOnClickListener {

            val context = requireContext()

            if (!UserPrefs.hasUserInfo(context)) {

                parentFragmentManager.beginTransaction()
                    .replace(R.id.contentContainer, InfoFragment())
                    .addToBackStack(null)
                    .commit()

            } else {

                parentFragmentManager.beginTransaction()
                    .replace(R.id.contentContainer, ShowFragment())
                    .addToBackStack(null)
                    .commit()

            }
        }

        btnset.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.contentContainer, SettingFragment())
                .addToBackStack(null)
                .commit()
        }

    }
}
