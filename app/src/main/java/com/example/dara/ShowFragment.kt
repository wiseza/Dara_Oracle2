package com.example.dara

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

// 1. Data Class สำหรับเก็บสี
data class DailyColor(
    val work: List<String>,
    val money: List<String>,
    val love: List<String>,
    val health: List<String>
)

class ShowFragment : Fragment(R.layout.fragment_show) {

    // 2. สร้าง Map ข้อมูลสี (ย้ายมาไว้ใน Class เพื่อความเป็นระเบียบ)
    private val colorMap = mapOf(
        Calendar.SUNDAY to DailyColor(listOf("#6F31B1", "#000000"), listOf("#006400", "#32CD32"), listOf("#FF69B4", "#FFC0CB"), listOf("#6F31B1", "#8A2BE2")),
        Calendar.MONDAY to DailyColor(listOf("#4E2F1A", "#FF7F27"), listOf("#6F31B1", "#000000"), listOf("#006400", "#32CD32"), listOf("#4169E1", "#00BFFF")),
        Calendar.TUESDAY to DailyColor(listOf("#6F31B1", "#8A2BE2"), listOf("#4E2F1A", "#FF7F27"), listOf("#6F31B1", "#000000"), listOf("#CC0000", "#FF0000")),
        Calendar.WEDNESDAY to DailyColor(listOf("#4169E1", "#00BFFF"), listOf("#6F31B1", "#8A2BE2"), listOf("#4E2F1A", "#FF7F27"), listOf("#FFFF00", "#C0C0C0")),
        Calendar.THURSDAY to DailyColor(listOf("#FFFF00", "#FFFFFF"), listOf("#CC0000", "#FF0000"), listOf("#4169E1", "#00BFFF"), listOf("#006400", "#32CD32")),
        Calendar.FRIDAY to DailyColor(listOf("#006400", "#32CD32"), listOf("#FF69B4", "#FFC0CB"), listOf("#FFFF00", "#FFFFFF"), listOf("#4E2F1A", "#FF7F27")),
        Calendar.SATURDAY to DailyColor(listOf("#CC0000", "#FF0000"), listOf("#4169E1", "#00BFFF"), listOf("#6F31B1", "#8A2BE2"), listOf("#FF69B4", "#FFC0CB"))
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- ส่วนที่ 1: จัดการวันที่ปัจจุบัน ---
        val calendar = Calendar.getInstance()
        val localeThai = Locale("th", "TH")

        // แสดงชื่อวัน (เช่น วันศุกร์) - ตรวจสอบ ID ใน XML ให้ตรงกับ tv_day_name
        val tvDayName = view.findViewById<TextView>(R.id.tv_day_name)
        tvDayName?.text = SimpleDateFormat("EEEE", localeThai).format(calendar.time)

        // แสดงวันที่ เดือน ปี พ.ศ. - ตรวจสอบ ID ให้ตรงกับ tv_full_date
        val tvFullDate = view.findViewById<TextView>(R.id.tv_full_date)
        val dayMonth = SimpleDateFormat("dd MMMM", localeThai).format(calendar.time)
        val thaiYear = calendar.get(Calendar.YEAR) + 543
        tvFullDate?.text = "$dayMonth $thaiYear"

        // --- ส่วนที่ 2: จัดการสีมงคลตามวัน ---
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val todayColors = colorMap[dayOfWeek]

        todayColors?.let { colors ->
            // การงาน (Work)
            updateCircleColor(view.findViewById(R.id.img_work_1), colors.work[0])
            if (colors.work.size > 1) updateCircleColor(view.findViewById(R.id.img_work_2), colors.work[1])

            // การเงิน (Money)
            updateCircleColor(view.findViewById(R.id.img_money_1), colors.money[0])
            if (colors.money.size > 1) updateCircleColor(view.findViewById(R.id.img_money_2), colors.money[1])

            // ความรัก (Love)
            updateCircleColor(view.findViewById(R.id.img_love_1), colors.love[0])
            if (colors.love.size > 1) updateCircleColor(view.findViewById(R.id.img_love_2), colors.love[1])

            // สุขภาพ (Health)
            updateCircleColor(view.findViewById(R.id.img_health_1), colors.health[0])
            if (colors.health.size > 1) updateCircleColor(view.findViewById(R.id.img_health_2), colors.health[1])
        }


        // --- ส่วนที่ 3: ปุ่ม Navigation ---
        val btnNext = view.findViewById<ImageView>(R.id.btnshow_next)
        btnNext?.setOnClickListener {
            (activity as? MainActivity)?.openTab(CalendarFragment(), 1)
        }
    }

    // ฟังก์ชันช่วยเปลี่ยนสีในวงกลม (Shape)
    private fun updateCircleColor(imageView: ImageView?, hexColor: String) {
        imageView?.let {
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.circle_shape) as GradientDrawable
            // ต้องทำการ mutate() เพื่อไม่ให้ ImageView ตัวอื่นที่ใช้ drawable เดียวกันเปลี่ยนสีตามทั้งหมด
            val newDrawable = drawable.mutate() as GradientDrawable
            newDrawable.setColor(Color.parseColor(hexColor))
            it.setImageDrawable(newDrawable)
        }
    }
}