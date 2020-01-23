package com.triardn.kadesubmission.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeam")
    var strTeam: String? = null,

    @SerializedName("strStadium")
    var strStadium: String? = null,

    @SerializedName("strStadiumThumb")
    var strStadiumThumb: String? = null,

    @SerializedName("strStadiumDescription")
    var strStadiumDescription: String? = null,

    @SerializedName("strStadiumLocation")
    var strStadiumLocation: String? = null,

    @SerializedName("intStadiumCapacity")
    var intStadiumCapacity: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null,

    @SerializedName("strTeamBadge")
    var strTeamBadge: String? = null,

    @SerializedName("strTeamJersey")
    var strTeamJersey: String? = null
) : Parcelable