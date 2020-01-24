package com.triardn.kadesubmission

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.triardn.kadesubmission.adapter.ClubAdapter
import com.triardn.kadesubmission.model.Team
import com.triardn.kadesubmission.presenter.ClubPresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.ClubListView
import kotlinx.android.synthetic.main.activity_club_list.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchTeamActivity: AppCompatActivity(), View.OnClickListener, ClubListView {
    private lateinit var presenter: ClubPresenter
    private lateinit var adapter: ClubAdapter
    private var clubList: MutableList<Team> = mutableListOf()

    var query: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        val actionbar = supportActionBar
        actionbar?.title = "Search Team"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        adapter = ClubAdapter(clubList)
        club_list.adapter = adapter
        club_list.layoutManager = LinearLayoutManager(this)

        btn_search.setOnClickListener(this)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = ClubPresenter(this, apiRepository, gson)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_search -> {
                query = search_query.text.toString()
                if (TextUtils.isEmpty(query)) {
                    return
                }

                presenter.searchClub(query)
            }
        }
    }

    override fun showClubList(data: List<Team>) {
        clubList.clear()

        if (data.isNotEmpty()) {
            clubList.addAll(data)
        } else {
            Toast.makeText(this, "Team not found", Toast.LENGTH_SHORT).show()
        }

        adapter.notifyDataSetChanged()
    }
}