package com.triardn.kadesubmission.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item (
    var id: Int,
    var name: String,
    var image: Int
) : Parcelable
