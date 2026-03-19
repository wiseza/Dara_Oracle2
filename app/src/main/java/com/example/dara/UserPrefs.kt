package com.example.dara

import android.content.Context
import android.content.SharedPreferences
import java.util.Calendar

object UserPrefs {
    private const val PREFS_NAME = "user_prefs"
    private const val KEY_DAY = "user_day"
    private const val KEY_ZODIAC = "user_zodiac"
    private const val KEY_LAST_UPDATE = "last_update_day"
    private const val KEY_WORK_CARD = "work_card_index"
    private const val KEY_LOVE_CARD = "love_card_index"
    private const val KEY_MONEY_CARD = "money_card_index"
    private const val KEY_HEALTH_CARD = "health_card_index"

    private fun getPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUserInfo(context: Context, day: String, zodiac: String) {
        val editor = getPrefs(context).edit()
        editor.putString(KEY_DAY, day)
        editor.putString(KEY_ZODIAC, zodiac)
        editor.apply()
    }

    fun getUserDay(context: Context): String? =
        getPrefs(context).getString(KEY_DAY, null)

    fun getUserZodiac(context: Context): String? =
        getPrefs(context).getString(KEY_ZODIAC, null)

    fun hasUserInfo(context: Context): Boolean =
        !getUserDay(context).isNullOrEmpty() && !getUserZodiac(context).isNullOrEmpty()

    fun saveCardIndices(context: Context, work: Int, love: Int, money: Int, health: Int) {
        val today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        val editor = getPrefs(context).edit()
        editor.putInt(KEY_WORK_CARD, work)
        editor.putInt(KEY_LOVE_CARD, love)
        editor.putInt(KEY_MONEY_CARD, money)
        editor.putInt(KEY_HEALTH_CARD, health)
        editor.putInt(KEY_LAST_UPDATE, today)
        editor.apply()
    }

    fun saveSingleCard(context: Context, index: Int, category: String) {
        val today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        val editor = getPrefs(context).edit()

        when(category){
            "work"  -> editor.putInt(KEY_WORK_CARD, index)
            "love"  -> editor.putInt(KEY_LOVE_CARD, index)
            "money" -> editor.putInt(KEY_MONEY_CARD, index)
            "health"-> editor.putInt(KEY_HEALTH_CARD, index)
        }
        editor.putInt(KEY_LAST_UPDATE, today)
        editor.apply()
    }

    fun getWorkCardIndex(context: Context): Int =
        getPrefs(context).getInt(KEY_WORK_CARD, -1)

    fun getLoveCardIndex(context: Context): Int =
        getPrefs(context).getInt(KEY_LOVE_CARD, -1)

    fun getMoneyCardIndex(context: Context): Int =
        getPrefs(context).getInt(KEY_MONEY_CARD, -1)

    fun getHealthCardIndex(context: Context): Int =
        getPrefs(context).getInt(KEY_HEALTH_CARD, -1)

    fun isTodayFortuneSaved(context: Context): Boolean {
        val lastUpdate = getPrefs(context).getInt(KEY_LAST_UPDATE, -1)
        val today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        return lastUpdate == today
    }

    fun isAllCategoriesDrawn(context: Context): Boolean {
        return getWorkCardIndex(context) != -1 &&
                getLoveCardIndex(context) != -1 &&
                getMoneyCardIndex(context) != -1 &&
                getHealthCardIndex(context) != -1

    }
    fun saveSingleCardIndex(context: Context, category: String, index: Int) {
        val work = if (category == "work") index else getWorkCardIndex(context)
        val love = if (category == "love") index else getLoveCardIndex(context)
        val money = if (category == "money") index else getMoneyCardIndex(context)
        val health = if (category == "health") index else getHealthCardIndex(context)
        saveCardIndices(context, work, love, money, health)
    }
    fun clearFortuneData(context: Context) {
        val editor = getPrefs(context).edit()
        editor.remove(KEY_WORK_CARD)
        editor.remove(KEY_LOVE_CARD)
        editor.remove(KEY_MONEY_CARD)
        editor.remove(KEY_HEALTH_CARD)
        editor.remove(KEY_LAST_UPDATE)
        editor.apply()
    }

    fun clearUserInfo(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}