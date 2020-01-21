package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.TestContextProvider
import com.triardn.kadesubmission.model.League
import com.triardn.kadesubmission.model.LeagueResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.DetailView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPresenterTest {
    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRepository, gson, TestContextProvider() )
    }

    @Test
    fun getDetailLeagueTest() {
        val leagues: MutableList<League> = mutableListOf()
        val response = LeagueResponse(leagues)
        val leagueID = 4328

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getDetailLeague(leagueID)

            if (response.leagues.isNotEmpty()) {
                Mockito.verify(view).getDetailLeague(response.leagues[0])
            }
        }
    }
}