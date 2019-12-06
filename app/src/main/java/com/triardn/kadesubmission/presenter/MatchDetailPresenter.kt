package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.model.ScheduleResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.repository.TheSportsDBApi
import com.triardn.kadesubmission.view.MatchDetailView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val api: ApiRepository,
                           private val gson: Gson) {
    fun getMatchDetail(matchID: String) {
        doAsync {
            val data = gson.fromJson(api.doRequest(TheSportsDBApi.getMatchDetail(matchID)),
                ScheduleResponse::class.java
            )

            uiThread {
                view.getMatchDetail(data.schedules[0])
            }
        }
    }
}