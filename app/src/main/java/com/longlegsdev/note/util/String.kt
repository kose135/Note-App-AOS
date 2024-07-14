package com.longlegsdev.note.util

fun String.firstUpper(): String {
    return this.trim().lowercase().capitalize()
}

fun Int.twoDigit(): String {
    return "%02d".format(this)
}

fun Int.threeDigit(): String {
    return "%03d".format(this)
}
