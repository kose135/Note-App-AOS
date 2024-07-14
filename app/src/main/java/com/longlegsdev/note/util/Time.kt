package com.longlegsdev.note.util

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object Time {

    val currentTime: String
        get() = this.currentTime("yyyy-MM-dd HH:mm:ss")

    fun currentTime(format: String): String {
        val zone = ZoneId.of("Asia/Seoul")
        val currentDateTime = LocalDateTime.now(zone)
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(format)

        return currentDateTime.format(formatter)
    }
}