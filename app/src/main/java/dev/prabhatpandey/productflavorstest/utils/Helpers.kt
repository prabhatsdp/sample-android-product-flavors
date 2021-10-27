package dev.prabhatpandey.productflavorstest.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created By Prabhat Pandey for ProductFlavorsTest project
 * on Wednesday, 27 October, 2021 at 7:35 AM
 */

object Helpers {
    fun dateToStringLastUpdateTime(date: Date?): String {
        val dateNotNull = date ?: Date()
        val simpleDateFormatter = SimpleDateFormat("dd MMM, hh:mm a", Locale.ENGLISH)
        return simpleDateFormatter.format(dateNotNull)
    }
}