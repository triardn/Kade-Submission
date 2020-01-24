package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.CoroutineContextProvider
import com.triardn.kadesubmission.model.TeamResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.repository.TheSportsDBApi
import com.triardn.kadesubmission.view.ClubListView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ClubPresenter(private val view: ClubListView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson,
                        private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getClubList(leagueID: Int?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getLeagueTeams(leagueID)).await(),
                TeamResponse::class.java)

            view.showClubList(data.teams)
        }
    }

    fun searchClub(teamName: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.searchTeam(teamName.orEmpty())).await(),
                TeamResponse::class.java)

            view.showClubList(data.teams)
        }
    }
}