package com.triardn.kadesubmission.view

import com.triardn.kadesubmission.model.Schedule
import com.triardn.kadesubmission.model.Team

interface MatchDetailView {
    fun getMatchDetail(matchDetail: Schedule, homeTeam: Team, awayTeam: Team)
}