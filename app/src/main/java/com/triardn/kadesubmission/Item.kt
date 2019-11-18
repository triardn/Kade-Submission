package com.triardn.kadesubmission

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item (
    var name: String,
    var image: Int,
    var desc: String
) : Parcelable
