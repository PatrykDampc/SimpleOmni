package com.dampc.simpleomni.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Content(
    val title: String,
    val text: String
) : Parcelable