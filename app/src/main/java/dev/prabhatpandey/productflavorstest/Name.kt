package dev.prabhatpandey.productflavorstest

import com.google.firebase.firestore.ServerTimestamp
import com.google.type.DateTime
import java.util.*

/**
 * Created By Prabhat Pandey for ProductFlavorsTest project
 * on Monday, 18 October, 2021 at 8:02 AM
 */

data class Name(
    var title: String? = null,
    @ServerTimestamp
    var time: Date? = null
)
