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

// โครงสร้างข้อมูลสำหรับเก็บรายละเอียด (ต้องมีสี 2 สีเสมอเพื่อให้ขึ้น 2 วงตามภาพ)
data class LuckDetail(val colors: List<String>, val description: String)
data class DayInfo(
    val work: LuckDetail,
    val money: LuckDetail,
    val love: LuckDetail,
    val health: LuckDetail,
    val bad: LuckDetail
)

class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    // ข้อมูลสีอ้างอิงตาม "ตารางตารางสีเสื้อประจำวัน" (อัปเดตสีให้ตรงเป๊ะ)
    private val fullLuckMap = mapOf(
        Calendar.SUNDAY to DayInfo(
            LuckDetail(listOf("#6F31B1", "#000000"), "แก้ปัญหาเก่ง มีไหวพริบ"), // การงาน: ม่วง-ดำ
            LuckDetail(
                listOf("#006400", "#32CD32"),
                "เหนี่ยวทรัพย์ รับโชค"
            ),    // การเงิน: เขียวเข้ม-เขียว
            LuckDetail(
                listOf("#FF69B4", "#FFC0CB"),
                "รักหวานแหวว"
            ),           // ความรัก: ชมพูเข้ม-ชมพูอ่อน
            LuckDetail(
                listOf("#6F31B1", "#8A2BE2"),
                "เสริมภูมิคุ้มกัน กระฉับกระเฉง"
            ), // สุขภาพ: ม่วง-ม่วงสว่าง
            LuckDetail(
                listOf("#1976D2", "#29B6F6"),
                "การเงินรั่วไหล เก็บเงินไม่อยู่"
            ) // กาลกิณี (คงเดิม)
        ),
        Calendar.MONDAY to DayInfo(
            LuckDetail(
                listOf("#4E2F1A", "#FF7F27"),
                "ผู้ใหญ่เมตตา งานราบรื่น"
            ), // การงาน: น้ำตาล-ส้ม
            LuckDetail(listOf("#6F31B1", "#000000"), "ดึงดูดโชคลาภ"),           // การเงิน: ม่วง-ดำ
            LuckDetail(
                listOf("#006400", "#32CD32"),
                "รักสดใส มีเสน่ห์"
            ),       // ความรัก: เขียวเข้ม-เขียว
            LuckDetail(
                listOf("#4169E1", "#00BFFF"),
                "ร่างกายแข็งแรง ไร้โรคภัย"
            ), // สุขภาพ: น้ำเงิน-ฟ้า
            LuckDetail(listOf("#D32F2F", "#D32F2F"), "อุปสรรคเยอะ ติดขัดบ่อย")
        ),
        Calendar.TUESDAY to DayInfo(
            LuckDetail(
                listOf("#6F31B1", "#8A2BE2"),
                "งานเดิน ก้าวหน้า"
            ),      // การงาน: ม่วง-ม่วงสว่าง
            LuckDetail(
                listOf("#4E2F1A", "#FF7F27"),
                "เงินทองไหลมาเทมา"
            ),      // การเงิน: น้ำตาล-ส้ม
            LuckDetail(listOf("#6F31B1", "#000000"), "มีเสน่ห์ลึกลับ"),         // ความรัก: ม่วง-ดำ
            LuckDetail(
                listOf("#CC0000", "#FF0000"),
                "เสริมสร้างพลังกาย พลังใจ"
            ), // สุขภาพ: แดงเข้ม-แดง
            LuckDetail(listOf("#FBC02D", "#9E9E9E"), "ระวังความขัดแย้ง")
        ),
        Calendar.WEDNESDAY to DayInfo(
            LuckDetail(
                listOf("#4169E1", "#00BFFF"),
                "เจรจาสำเร็จ"
            ),           // การงาน: น้ำเงิน-ฟ้า
            LuckDetail(
                listOf("#6F31B1", "#8A2BE2"),
                "รับทรัพย์ไม่ขาดมือ"
            ),      // การเงิน: ม่วง-ม่วงสว่าง
            LuckDetail(
                listOf("#4E2F1A", "#FF7F27"),
                "ความรักมั่นคง"
            ),          // ความรัก: น้ำตาล-ส้ม
            LuckDetail(
                listOf("#FFFF00", "#C0C0C0"),
                "จิตใจแจ่มใส ผ่อนคลาย"
            ),   // สุขภาพ: เหลือง-เทา
            LuckDetail(listOf("#F06292", "#F06292"), "โชคลาภสะดุด")
        ),
        Calendar.THURSDAY to DayInfo(
            LuckDetail(
                listOf("#FFFF00", "#FFFFFF"),
                "เริ่มต้นงานใหม่ได้ดี"
            ),    // การงาน: เหลือง-ขาว
            LuckDetail(
                listOf("#CC0000", "#FF0000"),
                "เฮงๆ ปังๆ"
            ),             // การเงิน: แดงเข้ม-แดง
            LuckDetail(
                listOf("#4169E1", "#00BFFF"),
                "เข้าใจกันดี"
            ),           // ความรัก: น้ำเงิน-ฟ้า
            LuckDetail(
                listOf("#006400", "#32CD32"),
                "ฟื้นฟูร่างกาย ป้องกันโรค"
            ), // สุขภาพ: เขียวเข้ม-เขียว
            LuckDetail(listOf("#7B1FA2", "#212121"), "ผู้ใหญ่ไม่สนับสนุน")
        ),
        Calendar.FRIDAY to DayInfo(
            LuckDetail(
                listOf("#006400", "#32CD32"),
                "ไอเดียพุ่ง งานสร้างสรรค์"
            ), // การงาน: เขียวเข้ม-เขียว
            LuckDetail(
                listOf("#FF69B4", "#FFC0CB"),
                "เงินเข้าง่าย จ่ายคล่อง"
            ),   // การเงิน: ชมพูเข้ม-ชมพูอ่อน
            LuckDetail(
                listOf("#FFFF00", "#FFFFFF"),
                "รักบริสุทธิ์"
            ),           // ความรัก: เหลือง-ขาว
            LuckDetail(
                listOf("#4E2F1A", "#FF7F27"),
                "ระบบไหลเวียนดี สดชื่น"
            ),    // สุขภาพ: น้ำตาล-ส้ม
            LuckDetail(listOf("#7B1FA2", "#7B1FA2"), "งานล่าช้า แบกภาระหนัก")
        ),
        Calendar.SATURDAY to DayInfo(
            LuckDetail(
                listOf("#CC0000", "#FF0000"),
                "มีพลัง อำนาจ"
            ),          // การงาน: แดงเข้ม-แดง
            LuckDetail(
                listOf("#4169E1", "#00BFFF"),
                "โชคลาภก้อนใหญ่"
            ),         // การเงิน: น้ำเงิน-ฟ้า
            LuckDetail(
                listOf("#6F31B1", "#8A2BE2"),
                "รักโรแมนติก"
            ),           // ความรัก: ม่วง-ม่วงสว่าง
            LuckDetail(
                listOf("#FF69B4", "#FFC0CB"),
                "สุขภาพคงที่ ลดความเครียด"
            ), // สุขภาพ: ชมพูเข้ม-ชมพูอ่อน
            LuckDetail(listOf("#388E3C", "#8BC34A"), "รักมีปัญหา ผิดใจกันง่าย")
        )
    )

    private lateinit var tabWork: View
    private lateinit var tabLove: View
    private lateinit var tabMoney: View
    private lateinit var tabHealth: View
    private lateinit var tabBad: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()
        val localeThai = Locale("th", "TH")

        val tvDayName = view.findViewById<TextView>(R.id.tv_day_name_cal)
        tvDayName?.text = SimpleDateFormat("EEEE", localeThai).format(calendar.time)

        // 2. แสดงวันที่ เดือน ปี พ.ศ.
        val tvFullDate = view.findViewById<TextView>(R.id.tv_full_date_cal)
        val dayMonth = SimpleDateFormat("dd MMMM", localeThai).format(calendar.time)
        val thaiYear = calendar.get(Calendar.YEAR) + 543
        tvFullDate?.text = "$dayMonth $thaiYear"

        view.findViewById<View>(R.id.btnback_cal)?.setOnClickListener {
            (activity as? MainActivity)?.openTab(ShowFragment(), 0)
        }

        tabWork = view.findViewById(R.id.tab_work) ?: return
        tabLove = view.findViewById(R.id.tab_love) ?: return
        tabMoney = view.findViewById(R.id.tab_money) ?: return
        tabHealth = view.findViewById(R.id.tab_health) ?: return
        tabBad = view.findViewById(R.id.tab_bad) ?: return

        val tvHeader = view.findViewById<TextView>(R.id.tv_detail_header)
        val tvBody = view.findViewById<TextView>(R.id.tv_detail_body)

        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val todayData = fullLuckMap[dayOfWeek]

        todayData?.let { data ->
            updateCircleColor(view.findViewById(R.id.img_work_1), data.work.colors.getOrNull(0))
            updateCircleColor(view.findViewById(R.id.img_work_2), data.work.colors.getOrNull(1))

            updateCircleColor(view.findViewById(R.id.img_love_1), data.love.colors.getOrNull(0))
            updateCircleColor(view.findViewById(R.id.img_love_2), data.love.colors.getOrNull(1))

            updateCircleColor(view.findViewById(R.id.img_money_1), data.money.colors.getOrNull(0))
            updateCircleColor(view.findViewById(R.id.img_money_2), data.money.colors.getOrNull(1))

            updateCircleColor(view.findViewById(R.id.img_health_1), data.health.colors.getOrNull(0))
            updateCircleColor(view.findViewById(R.id.img_health_2), data.health.colors.getOrNull(1))

            updateCircleColor(view.findViewById(R.id.img_bad_1), data.bad.colors.getOrNull(0))
            updateCircleColor(view.findViewById(R.id.img_bad_2), data.bad.colors.getOrNull(1))
        }

        fun updateContent(category: String, detail: LuckDetail, selectedTab: View) {
            tvHeader.text = category
            tvBody.text = "• ${detail.description}"

            tabWork.setBackgroundColor(Color.TRANSPARENT)
            tabLove.setBackgroundColor(Color.TRANSPARENT)
            tabMoney.setBackgroundColor(Color.TRANSPARENT)
            tabHealth.setBackgroundColor(Color.TRANSPARENT)
            tabBad.setBackgroundColor(Color.TRANSPARENT)

            val selectedShape = GradientDrawable().apply {
                cornerRadius = 40f // ปรับความมนของกล่องเทา
                setColor(Color.parseColor("#EAEAEA"))
            }
            selectedTab.background = selectedShape
        }

        todayData?.let { data ->
            tabWork.setOnClickListener { updateContent("การงาน", data.work, tabWork) }
            tabLove.setOnClickListener { updateContent("ความรัก", data.love, tabLove) }
            tabMoney.setOnClickListener { updateContent("การเงิน", data.money, tabMoney) }
            tabHealth.setOnClickListener { updateContent("สุขภาพ", data.health, tabHealth) }
            tabBad.setOnClickListener { updateContent("หลีกเลี่ยง", data.bad, tabBad) }

            updateContent("การงาน", data.work, tabWork)
        }
    }

    private fun updateCircleColor(targetView: View?, hexColor: String?) {
        if (targetView == null || hexColor.isNullOrEmpty()) {
            targetView?.visibility = View.GONE
            return
        }

        targetView.visibility = View.VISIBLE
        try {
            // เปลี่ยนมาโหลดจาก R.drawable.circle_shape เหมือนภาพที่ 2
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.circle_shape)
                ?.mutate() as? GradientDrawable

            drawable?.let {
                it.setColor(Color.parseColor(hexColor))

                // ถ้าอยากบังคับให้ขอบเป็นสีดำเป๊ะๆ แม้ใน XML จะเป็นสีอื่น
                // it.setStroke(4, Color.BLACK)

                if (targetView is ImageView) {
                    targetView.setImageDrawable(it)
                } else {
                    targetView.background = it
                }
            }
        } catch (e: Exception) {
            targetView.visibility = View.GONE
        }
    }
}