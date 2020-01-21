package com.triardn.kadesubmission.presenter

import com.google.gson.Gson
import com.triardn.kadesubmission.TestContextProvider
import com.triardn.kadesubmission.model.Schedule
import com.triardn.kadesubmission.model.ScheduleResponse
import com.triardn.kadesubmission.model.SearchResponse
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.ScheduleView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SchedulePresenterTest {
    @Mock
    private lateinit var view: ScheduleView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SchedulePresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = SchedulePresenter(view, apiRepository, gson, TestContextProvider() )
    }

    @Test
    fun getPreviousMatchesTest() {
        val schedules: MutableList<Schedule> = mutableListOf()
        val response = ScheduleResponse(schedules)
        val league = 4328

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ScheduleResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPreviousMatches(league)

            Mockito.verify(view).showLeagueMatches(response.schedules)
        }
    }

    @Test
    fun getNextMatchesTest() {
        val schedules: MutableList<Schedule> = mutableListOf()
        val response = ScheduleResponse(schedules)
        val league = 4328

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    ScheduleResponse::class.java
                )
            ).thenReturn(response)

            presenter.getNextMatches(league)

            Mockito.verify(view).showLeagueMatches(response.schedules)
        }
    }

    @Test
    fun searchMatchTest() {
        val schedules: MutableList<Schedule> = mutableListOf()
        val response = SearchResponse(schedules)
        val query = "Barcelona"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    SearchResponse::class.java
                )
            ).thenReturn(response)

            presenter.searchMatch(query)

            Mockito.verify(view).showLeagueMatches((response.schedules?.filter { it.strSport == "Soccer" }) ?: listOf())
        }
    }
}
