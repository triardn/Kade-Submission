package com.triardn.kadesubmission

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.triardn.kadesubmission.adapter.ClubAdapter
import com.triardn.kadesubmission.model.League
import com.triardn.kadesubmission.model.Team
import com.triardn.kadesubmission.presenter.ClubPresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.ClubListView
import kotlinx.android.synthetic.main.activity_club_list.*
import org.jetbrains.anko.startActivity

class ClubListActivity : AppCompatActivity(), ClubListView {
    private var clubList: MutableList<Team> = mutableListOf()
    private lateinit var presenter: ClubPresenter
    private lateinit var adapter: ClubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club_list)

        val league = intent.getParcelableExtra<League>("EXTRA_ITEM")

        val actionbar = supportActionBar
        actionbar?.title = league?.strLeague
        actionbar?.setDisplayHomeAsUpEnabled(true)

        adapter = ClubAdapter(clubList)
        club_list.adapter = adapter
        club_list.layoutManager = LinearLayoutManager(this)

        val request = ApiRepository()
        val gson = Gson()
        presenter = ClubPresenter(this, request, gson)
        presenter.getClubList(league?.idLeague?.toInt())
    }

    override fun showClubList(data: List<Team>) {
        clubList.clear()
        clubList.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.team_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_search_team_menu -> {
                startActivity<SearchTeamActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}