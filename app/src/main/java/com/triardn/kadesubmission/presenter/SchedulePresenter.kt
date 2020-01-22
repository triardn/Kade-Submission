package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.CoroutineContextProvider
import com.triardn.kadesubmission.model.ScheduleResponse
import com.triardn.kadesubmission.model.SearchResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.repository.TheSportsDBApi
import com.triardn.kadesubmission.view.ScheduleView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SchedulePresenter(private val view: ScheduleView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson,
                        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPreviousMatches(leagueID: Int?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getPreviousMatches(leagueID)).await(),
                ScheduleResponse::class.java)
            view.showLeagueMatches(data.schedules)
        }
    }

    fun getNextMatches(leagueID: Int?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getNextMatches(leagueID)).await(),
                ScheduleResponse::class.java)
            view.showLeagueMatches(data.schedules)
        }
    }

    fun searchMatch(query: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.searchMatch(query.orEmpty())).await(),
                SearchResponse::class.java)

            val item = (data.schedules?.filter { it.strSport == "Soccer" }) ?: listOf()

            view.showLeagueMatches(item)
        }
    }
}