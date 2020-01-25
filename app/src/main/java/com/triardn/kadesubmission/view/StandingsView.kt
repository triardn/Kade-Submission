package com.triardn.kadesubmission.view

import com.triardn.kadesubmission.model.Standing

interface StandingsView {
    fun getLeagueStandings(data: List<Standing>)
}