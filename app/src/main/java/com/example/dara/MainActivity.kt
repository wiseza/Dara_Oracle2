package com.example.dara


import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dara.CategoriesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(HomeFragment())
        setActiveTab(0)

        findViewById<View>(R.id.homeContainer).setOnClickListener {
            openTab(HomeFragment(),0)
        }

        findViewById<View>(R.id.calendarContainer).setOnClickListener {
            openTab(CalendarFragment(),1)
        }

        findViewById<View>(R.id.categoriesContainer).setOnClickListener {
            openTab(CategoriesFragment(),2)
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

        // reset ทุกอันก่อน
        val defaultColor = getColor(R.color.light_purple)
        val activeColor = getColor(R.color.black_purple)
        val defaultIconColor = getColor(R.color.black)
        val activeIconColor = getColor(R.color.white)

        homeCircle.setColorFilter(defaultColor)
        calendarCircle.setColorFilter(defaultColor)
        cardCircle.setColorFilter(defaultColor)

        homeIcon.setColorFilter(defaultIconColor)
        calendarIcon.setColorFilter(defaultIconColor)
        cardIcon.setColorFilter(defaultIconColor)

        homeCircle.animate()
            .translationY(0f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(200)
            .start()
        calendarCircle.animate()
            .translationY(0f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(180)
            .start()
        cardCircle.animate()
            .translationY(0f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(180)
            .start()

        homeIcon.animate()
            .translationY(0f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(180)
            .start()
        calendarIcon.animate()
            .translationY(0f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(180)
            .start()
        cardIcon.animate()
            .translationY(0f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(180)
            .start()



        when (active) {
            0 -> {
                homeCircle.setColorFilter(activeColor)
                homeIcon.setColorFilter(activeIconColor)
                homeCircle.animate()
                    .translationY(-140f)
                    .scaleX(1.5f)
                    .scaleY(1.5f)
                    .setInterpolator(OvershootInterpolator())
                    .setDuration(200)
                    .start()
                homeIcon.animate()
                    .translationY(-140f)
                    .scaleX(1.5f)
                    .scaleY(1.5f)
                    .setInterpolator(OvershootInterpolator())
                    .setDuration(200)
                    .start()
            }
            1 -> {
                calendarCircle.setColorFilter(activeColor)
                calendarIcon.setColorFilter(activeIconColor)
                calendarCircle.animate()
                    .translationY(-140f)
                    .scaleX(1.5f)
                    .scaleY(1.5f)
                    .setInterpolator(OvershootInterpolator())
                    .setDuration(200)
                    .start()
                calendarIcon.animate()
                    .translationY(-140f)
                    .scaleX(1.5f)
                    .scaleY(1.5f)
                    .setInterpolator(OvershootInterpolator())
                    .setDuration(200)
                    .start()
            }
            2 -> {
                cardCircle.setColorFilter(activeColor)
                cardIcon.setColorFilter(activeIconColor)
                cardCircle.animate()
                    .translationY(-140f)
                    .scaleX(1.5f)
                    .scaleY(1.5f)
                    .setInterpolator(OvershootInterpolator())
                    .setDuration(200)
                    .start()
                cardIcon.animate()
                    .translationY(-140f)
                    .scaleX(1.5f)
                    .scaleY(1.5f)
                    .setInterpolator(OvershootInterpolator())
                    .setDuration(200)
                    .start()
            }
        }
    }

}


