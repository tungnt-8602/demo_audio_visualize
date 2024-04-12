package com.example.testeve.utils

import android.annotation.SuppressLint
import java.util.concurrent.TimeUnit

@SuppressLint("DefaultLocale")
fun Long.formatAsTime(): String {
    if(this >= 0){
        val seconds = (TimeUnit.MILLISECONDS.toSeconds(this) % 60).toInt()
        val minutes = (TimeUnit.MILLISECONDS.toMinutes(this) % 60).toInt()

        return when (val hours = (TimeUnit.MILLISECONDS.toHours(this)).toInt()) {
            0 -> "$minutes:$seconds"  // Format MM:SS
            else -> "$hours:$minutes:$seconds"  // Format HH:MM:SS
        }
    } else {
        return "00:00"
    }
}

// parse HH:MM:SS to long
@SuppressLint("DefaultLocale")
fun String.parseTimeToMillis(): Long? {
    val parts = this.split(":")
    return when (parts.size) {
        2 -> {
            // Format MM:SS
            val minutes = parts[0].toIntOrNull() ?: return null
            val seconds = parts[1].toIntOrNull() ?: return null
            if (minutes !in 0..59 || seconds !in 0..59) return null
            TimeUnit.MINUTES.toMillis(minutes.toLong()) + TimeUnit.SECONDS.toMillis(seconds.toLong())
        }
        3 -> {
            // Format HH:MM:SS
            val hours = parts[0].toIntOrNull() ?: return null
            val minutes = parts[1].toIntOrNull() ?: return null
            val seconds = parts[2].toIntOrNull() ?: return null
            if (hours < 0 || minutes !in 0..59 || seconds !in 0..59) return null
            TimeUnit.HOURS.toMillis(hours.toLong()) +
                    TimeUnit.MINUTES.toMillis(minutes.toLong()) +
                    TimeUnit.SECONDS.toMillis(seconds.toLong())
        }
        else -> null
    }
}
