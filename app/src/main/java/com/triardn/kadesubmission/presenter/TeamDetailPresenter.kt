package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.CoroutineContextProvider
import com.triardn.kadesubmission.model.TeamResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.repository.TheSportsDBApi
import com.triardn.kadesubmission.view.TeamDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(private val view: TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeamDetail(teamID: Int?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getTeamDetail(teamID.toString())).await(),
                TeamResponse::class.java)

            println(data)

            if (data.teams.isNotEmpty()) {
                view.getTeamDetail(data.teams[0])
            }
        }
    }
}