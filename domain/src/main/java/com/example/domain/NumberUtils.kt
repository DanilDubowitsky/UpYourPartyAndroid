package com.example.domain

import java.math.RoundingMode
import java.text.NumberFormat
import java.util.Locale

object NumberUtils {

    const val RATING_DISPLAY_DECIMALS = 2

    fun Float.formatToDisplay(
        displayDecimals: Int,
        locale: Locale = Locale.getDefault(),
        roundingMode: RoundingMode? = null
    ) = NumberFormat.getInstance().apply {
        maximumFractionDigits = displayDecimals
        this.roundingMode = roundingMode ?: RoundingMode.DOWN
    }.format(this)
}
