package org.devintensive.utils

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String = SimpleDateFormat(pattern, Locale("ru")).format(this)

fun Date.add(value: Long, timeUnits: TimeUnits): Date {
    val time = this.time
    time.plus(
        when (timeUnits) {
            TimeUnits.SECOND -> value * SECOND
            TimeUnits.MINUTE -> value * MINUTE
            TimeUnits.HOUR -> value * HOUR
            TimeUnits.DAY -> value * DAY
        }
    )
    this.time = time
    return this
}

fun Date.humanizeDiff(): String {
    val time = this.time
    val days = time / DAY
    val hours = time / HOUR
    val minutes = time / MINUTE
    val seconds = time / SECOND

    val currentTime = Date().time
    val currentDays = currentTime / DAY
    val currentHours = currentTime / HOUR
    val currentMinutes = currentTime / MINUTE
    val currentSeconds = currentTime / SECOND

    return when {
        currentTime - time in 0..1 -> "только что"
        currentSeconds - seconds in 1..45 -> "несколько секунд назад"
        currentSeconds - seconds in 1..45 -> "несколько секунд назад"
        currentSeconds - seconds in 45..75 -> "минуту назад"
        currentSeconds - seconds >= 75 && currentMinutes - minutes <= 45 -> "${currentMinutes - minutes} минут назад"
        currentMinutes - minutes in 45..75 -> "час назад"
        currentMinutes - minutes >= 75 && currentHours - hours <= 22 -> "${currentHours - hours} часов назад"
        currentHours - hours in 22..26 -> "день назад"
        currentHours - hours >= 26 && currentDays - days <= 360 -> "${currentDays - days} дней назад"
        currentDays - days > 360 -> "более года назад"
        else -> "более года назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}