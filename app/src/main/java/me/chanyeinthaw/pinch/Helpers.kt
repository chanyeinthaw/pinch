package me.chanyeinthaw.pinch

import java.text.SimpleDateFormat
import java.util.*

fun Date.toFormattedString() : String {
    val formatter = SimpleDateFormat("MMMM d, yyyy", Locale.US)

    return formatter.format(this)
}