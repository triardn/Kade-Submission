package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.model.LeagueResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.repository.TheSportsDBApi
import com.triardn.kadesubmission.view.DetailView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson) {
    fun getDetailLeague(leagueID: Int?) {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getLeague(leagueID)),
                LeagueResponse::class.java
            )

            uiThread {
                view.getDetailLeague(data.leagues[0])
            }
        }
    }
}
