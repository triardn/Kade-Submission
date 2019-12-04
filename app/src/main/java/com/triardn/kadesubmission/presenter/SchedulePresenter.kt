package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.model.LeagueResponse
import com.triardn.kadesubmission.model.ScheduleResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.repository.TheSportsDBApi
import com.triardn.kadesubmission.view.ScheduleView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SchedulePresenter(private val view: ScheduleView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson) {
    fun getPreviousMatches(leagueID: Int?) {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getPreviousMatches(leagueID)), ScheduleResponse::class.java)

            uiThread {
                view.showLeagueMatches(data.schedules)
            }
        }
    }

    fun getNextMatches(leagueID: Int?) {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getNextMatches(leagueID)), ScheduleResponse::class.java)

            uiThread {
                view.showLeagueMatches(data.schedules)
            }
        }
    }
}