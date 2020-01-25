package com.triardn.kadesubmission

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.triardn.kadesubmission.adapter.StandingsAdapter
import com.triardn.kadesubmission.model.League
import com.triardn.kadesubmission.model.Standing
import com.triardn.kadesubmission.presenter.StandingsPresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.StandingsView
import kotlinx.android.synthetic.main.activity_league_standings.*

class StandingsActivity: AppCompatActivity(), StandingsView {
    private val standings: MutableList<Standing> = mutableListOf()
    private lateinit var presenter: StandingsPresenter
    private lateinit var adapter: StandingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_standings)

        val league = intent.getParcelableExtra<League>("EXTRA_ITEM")

        val actionbar = supportActionBar
        actionbar?.title = "Standings - " + league?.strLeague
        actionbar?.setDisplayHomeAsUpEnabled(true)

        adapter = StandingsAdapter(standings)
        league_standings.adapter = adapter
        league_standings.layoutManager = LinearLayoutManager(this)

        val request = ApiRepository()
        val gson = Gson()
        presenter = StandingsPresenter(this, request, gson)
        presenter.getLeagueStandings(league?.idLeague?.toInt())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun getLeagueStandings(data: List<Standing>) {
        standings.clear()
        standings.addAll(data)
        adapter.notifyDataSetChanged()
    }
}