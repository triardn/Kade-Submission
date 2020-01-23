package com.triardn.kadesubmission.view

import com.triardn.kadesubmission.model.Team

interface ClubListView {
    fun showClubList(data: List<Team>)
}