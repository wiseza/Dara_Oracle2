package com.example.dara

import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!UserPrefs.isTodayFortuneSaved(this)) {
            UserPrefs.clearFortuneData(this)
        }

        replaceFragment(HomeFragment())
        setActiveTab(0)

        findViewById<View>(R.id.homeContainer).setOnClickListener {
            openTab(HomeFragment(), 0)
        }

        findViewById<View>(R.id.calendarContainer).setOnClickListener {
            openTab(CalendarFragment(), 1)
        }

        val categoriesContainer = findViewById<View>(R.id.categoriesContainer)

        categoriesContainer.setOnClickListener {

            if (!UserPrefs.hasUserInfo(this)) {
                Toast.makeText(this,"กรุณาใส่วันเกิดและราศีก่อน",Toast.LENGTH_SHORT).show()
                openTab(InfoFragment(), 0)

            } else {
                openTab(CategoriesFragment(), 2)

            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentContainer, fragment)
            .commit()
    }

    fun openTab(fragment: Fragment, tab: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentContainer, fragment)
            .commit()

        setActiveTab(tab)
    }

    private fun setActiveTab(active: Int) {
        val homeCircle = findViewById<ImageView>(R.id.homeCircle)
        val calendarCircle = findViewById<ImageView>(R.id.calendarCircle)
        val cardCircle = findViewById<ImageView>(R.id.cardCircle)

        val homeIcon = findViewById<ImageView>(R.id.btnHome)
        val calendarIcon = findViewById<ImageView>(R.id.btnCalendar)
        val cardIcon = findViewById<ImageView>(R.id.btnCard)


        val defaultColor = getColor(R.color.light_purple)
        val activeColor = getColor(R.color.black_purple)
        val defaultIconColor = getColor(R.color.black)
        val activeIconColor = getColor(R.color.white)


        listOf(homeCircle, calendarCircle, cardCircle).forEach {
            it.setColorFilter(defaultColor)
            it.animate()
                .translationY(0f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(200)
                .start()
        }

        listOf(homeIcon, calendarIcon, cardIcon).forEach {
            it.setColorFilter(defaultIconColor)
            it.animate()
                .translationY(0f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(200)
                .start()
        }

        when (active) {
            0 -> {
                homeCircle.setColorFilter(activeColor)
                homeIcon.setColorFilter(activeIconColor)
                animateButton(homeCircle, homeIcon)
            }
            1 -> {
                calendarCircle.setColorFilter(activeColor)
                calendarIcon.setColorFilter(activeIconColor)
                animateButton(calendarCircle, calendarIcon)
            }
            2 -> {
                cardCircle.setColorFilter(activeColor)
                cardIcon.setColorFilter(activeIconColor)
                animateButton(cardCircle, cardIcon)
            }
        }
    }

    private fun animateButton(circle: ImageView, icon: ImageView) {
        circle.animate()
            .translationY(-140f)
            .scaleX(1.5f)
            .scaleY(1.5f)
            .setInterpolator(OvershootInterpolator())
            .setDuration(200)
            .start()

        icon.animate()
            .translationY(-140f)
            .scaleX(1.5f)
            .scaleY(1.5f)
            .setInterpolator(OvershootInterpolator())
            .setDuration(200)
            .start()
    }
}