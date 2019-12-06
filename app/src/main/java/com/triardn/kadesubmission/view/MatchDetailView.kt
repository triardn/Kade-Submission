package com.triardn.kadesubmission.view

import com.triardn.kadesubmission.model.Schedule

interface MatchDetailView {
    fun getMatchDetail(data: Schedule)
}