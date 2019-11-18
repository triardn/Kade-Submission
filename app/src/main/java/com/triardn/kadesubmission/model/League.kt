package com.triardn.kadesubmission.model

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    var idLeague: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? = null,

    @SerializedName("strSport")
    var strSport: String? = null,

    @SerializedName("strBadge")
    var strBadge: String? = null,

    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String? = null
)