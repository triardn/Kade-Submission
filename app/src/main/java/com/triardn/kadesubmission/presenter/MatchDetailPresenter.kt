package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.CoroutineContextProvider
import com.triardn.kadesubmission.model.Schedule
import com.triardn.kadesubmission.model.TeamResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.repository.TheSportsDBApi
import com.triardn.kadesubmission.view.MatchDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val api: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeamDetail(matchDetail: Schedule) {
        GlobalScope.launch(context.main) {
            val homeTeam = gson.fromJson(api.doRequest(TheSportsDBApi.getTeamDetail(matchDetail.idHomeTeam.orEmpty())).await(),
                TeamResponse::class.java
            )

            val awayTeam = gson.fromJson(api.doRequest(TheSportsDBApi.getTeamDetail(matchDetail.idAwayTeam.orEmpty())).await(),
                TeamResponse::class.java)

            if (homeTeam.teams.isNotEmpty() && awayTeam.teams.isNotEmpty()) {
                view.getMatchDetail(matchDetail, homeTeam.teams[0], awayTeam.teams[0])
            }
        }
    }
}