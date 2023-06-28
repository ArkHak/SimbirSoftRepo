package o.mysin.simbirsoftappjava.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


// "07 июля 1996г."
fun convertTime(timestamp: Long): String {
    val date = Instant.fromEpochMilliseconds(timestamp * 1000)
        .toLocalDateTime(TimeZone.currentSystemDefault())
    return date.toString()

}





