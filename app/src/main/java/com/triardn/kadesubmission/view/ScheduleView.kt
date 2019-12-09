package com.triardn.kadesubmission.view

import com.triardn.kadesubmission.model.Schedule

interface ScheduleView {
    fun showLeagueMatches(data: List<Schedule>?)
}