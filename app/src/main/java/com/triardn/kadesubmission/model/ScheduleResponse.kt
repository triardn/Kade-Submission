package com.triardn.kadesubmission.model

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @SerializedName("events")
    val schedules: List<Schedule>
)