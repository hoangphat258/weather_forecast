package com.example.weatherforecast.utils

import android.text.format.DateFormat
import java.util.*

object Utils {

    fun getDate(timestamp: Long): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = timestamp * 1000L
        return DateFormat.format("EEE, dd MMM yyyy", calendar).toString()
    }

    fun capitalizeFirstCharacter(text: String?): String {
        if (text != null && text.isNotEmpty()) {
            return text[0].toUpperCase() + text.substring(1, text.length)
        }
        return ""
    }

}