package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.CoroutineContextProvider
import com.triardn.kadesubmission.model.StandingResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.repository.TheSportsDBApi
import com.triardn.kadesubmission.view.StandingsView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingsPresenter(private val view: StandingsView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getLeagueStandings(leagueID: Int?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getLeagueStandings(leagueID)).await(),
                StandingResponse::class.java)

            println(TheSportsDBApi.getLeagueStandings(leagueID))

            view.getLeagueStandings(data.table)
        }
    }
}