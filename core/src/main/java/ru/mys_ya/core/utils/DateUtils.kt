package ru.mys_ya.core.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


private val monthNames = arrayOf(
    "Январь",
    "Февраль",
    "Март",
    "Апрель",
    "Май",
    "Июнь",
    "Июль",
    "Август",
    "Сентябрь",
    "Октябрь",
    "Ноябрь",
    "Декабрь"
)

/**
 * Метод возвращает конвертацию даты в виде "07 июля 1996г."
 *
 * @param timestamp - дата <Timestamp>
 * @author Mys_ya
 */
fun convertDateTimeProfile(timestamp: Long): String {
    val date = convertTimestampToLocalDate(timestamp)
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy 'г.'", Locale("ru"))

    return formattingDate(date, formatter)
}

/**
 * Метод возвращает дату/перид+остаток между датами
 * - если это период - "Осталось 13 дней (21.09 – 20.10)"
 * - если это один день - "Октябрь 20, 2016"
 *
 * @param startDateTime - начальная дата <Timestamp>
 * @param endDateTime - конечная дата <Timestamp>
 * @author Mys_ya
 */
fun correctDateTime(startDateTime: Long, endDateTime: Long): String {
    val countDays = getDaysStartEndDate(startDateTime, endDateTime)
    return if (countDays > 0) {
        "Осталось $countDays дней ${getPeriodStartEndDate(startDateTime, endDateTime)}"
    } else {
        convertDateNews(startDateTime)
    }
}

/**
 * Метод возвращает конвертацию даты в виде "Октябрь 20, 2016"
 *
 * @param timestamp - дата <Timestamp>
 * @author Mys_ya
 */
fun convertDateNews(timestamp: Long): String {
    val date = convertTimestampToLocalDate(timestamp)
    val formatter = DateTimeFormatter.ofPattern("d, yyyy", Locale("ru"))
    val correctMonth = monthNames[date.monthNumber - 1]

    return "$correctMonth ${formattingDate(date, formatter)}"
}

/**
 * Метод возвращает перид между двумя датами в виде "(21.09 – 20.10)"
 *
 * @param startTimestamp - начальная дата <Timestamp>
 * @param endTimestamp - конечная дата <Timestamp>
 * @author Mys_ya
 */
fun getPeriodStartEndDate(startTimestamp: Long, endTimestamp: Long): String {
    val startDate = convertTimestampToLocalDate(startTimestamp)
    val endDate = convertTimestampToLocalDate(endTimestamp)
    val formatter = DateTimeFormatter.ofPattern("dd.MM", Locale("ru"))

    return "(${formattingDate(startDate, formatter)} – ${
        formattingDate(endDate, formatter)
    })"
}

private fun getDaysStartEndDate(startTimestamp: Long, endTimestamp: Long): Int {
    val startDate = convertTimestampToLocalDate(startTimestamp)
    val endDate = convertTimestampToLocalDate(endTimestamp)

    return startDate.daysUntil(endDate)
}

private fun convertTimestampToLocalDate(timestamp: Long): LocalDate {
    return Instant.fromEpochSeconds(timestamp)
        .toLocalDateTime(TimeZone.currentSystemDefault()).date
}

private fun formattingDate(date: LocalDate, formatter: DateTimeFormatter): String {
    return date.toJavaLocalDate().format(formatter)
}




