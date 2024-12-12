package com.rasyid.skinsense.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article (
    val title: String,
    val source: String,
    val image: Int,
    val desc: String,
    val url: String,
): Parcelable