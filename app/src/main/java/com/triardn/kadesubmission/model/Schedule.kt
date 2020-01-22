package com.triardn.kadesubmission.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Schedule(
    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("strEvent")
    var strEvent: String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam: String? = null,

    @SerializedName("idAwayTeam")
    var idAwayTeam: String? = null,

    @SerializedName("intHomeScore")
    var intHomeScore: String? = null,

    @SerializedName("intAwayScore")
    var intAwayScore: String? = null,

    @SerializedName("strDate")
    var strDate: String? = null,

    @SerializedName("strTime")
    var strTime: String? = null,

    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String? = null,

    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String? = null,

    @SerializedName("strHomeYellowCards")
    var strHomeYellowCards: String? = null,

    @SerializedName("strAwayYellowCards")
    var strAwayYellowCards: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null
) : Parcelable