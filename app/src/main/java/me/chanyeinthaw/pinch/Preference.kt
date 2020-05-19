package me.chanyeinthaw.pinch

import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.*

object Preference {
    private var sharedPreferences : SharedPreferences? = null

    private const val START_DATE = "START_DATE"
    var startDate : Long? = 0
        get() = sharedPreferences?.getLong(START_DATE, Long.MAX_VALUE)
        set(value) {
            sharedPreferences?.let {
                with(it.edit()) {
                    putLong(START_DATE, value ?: Long.MAX_VALUE)
                    commit()
                }
            }

            field = value
        }

    fun initialize(sharedPref: SharedPreferences) {
        sharedPreferences = sharedPref

        if (startDate == Long.MAX_VALUE) {
            val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US);
            val date = parser.parse("2019-10-20")

            date?.time?.let {
                startDate = it
            }
        }
    }
}