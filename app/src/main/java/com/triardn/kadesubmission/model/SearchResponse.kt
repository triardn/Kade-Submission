package com.triardn.kadesubmission.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("event")
    val schedules: List<Schedule>?
)