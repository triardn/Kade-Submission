package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.TestContextProvider
import com.triardn.kadesubmission.model.*
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.MatchDetailView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {

    @Mock
    private lateinit var view: MatchDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider() )
    }

    @Test
    fun getTeamDetailTest() {
        val leagues: MutableList<League> = mutableListOf()
        val response = LeagueResponse(leagues)
        val homeTeam: MutableList<Team> = mutableListOf()
        val homeTeamResponse = TeamResponse(homeTeam)
        val awayTeam: MutableList<Team> = mutableListOf()
        val awayTeamResponse = TeamResponse(awayTeam)
        val schedule = Schedule()

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    Schedule::class.java
                )
            ).thenReturn(schedule)

            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(homeTeamResponse)

            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(awayTeamResponse)

            presenter.getTeamDetail(schedule)

            if (response.leagues.isNotEmpty()) {
                Mockito.verify(view).getMatchDetail(schedule, homeTeamResponse.teams[0], awayTeamResponse.teams[0])
            }
        }
    }
}