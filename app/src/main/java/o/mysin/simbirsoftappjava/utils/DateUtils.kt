package o.mysin.simbirsoftappjava.utils

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

// "07 июля 1996г."
fun convertDateTimeProfile(timestamp: Long): String {
    val date = convertTimestampToLocalDate(timestamp)
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy 'г.'", Locale("ru"))


    return formattingDate(date, formatter)
}

//Октябрь 20, 2016
fun convertDateNews(timestamp: Long): String {
    val date = convertTimestampToLocalDate(timestamp)
    val formatter = DateTimeFormatter.ofPattern("d, yyyy", Locale("ru"))
    val correctMonth = monthNames[date.monthNumber - 1]

    return "$correctMonth ${formattingDate(date, formatter)}"
}

//(21.09 – 20.10)
fun getPeriodStartEndDate(startTimestamp: Long, endTimestamp: Long): String {
    val startDate = convertTimestampToLocalDate(startTimestamp)
    val endDate = convertTimestampToLocalDate(endTimestamp)
    val formatter = DateTimeFormatter.ofPattern("dd.MM", Locale("ru"))

    return "(${formattingDate(startDate, formatter)} – ${
        formattingDate(endDate, formatter)
    })"
}

fun getDaysStartEndDate(startTimestamp: Long, endTimestamp: Long): Int {
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




