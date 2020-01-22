package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.CoroutineContextProvider
import com.triardn.kadesubmission.model.LeagueResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.repository.TheSportsDBApi
import com.triardn.kadesubmission.view.DetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getDetailLeague(leagueID: Int?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getLeague(leagueID)).await(),
                LeagueResponse::class.java
            )

            if (data.leagues.isNotEmpty()) {
                view.getDetailLeague(data.leagues[0])
            }
        }
    }
}
