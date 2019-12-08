package com.triardn.kadesubmission

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.triardn.kadesubmission.adapter.ScheduleAdapter
import com.triardn.kadesubmission.model.Schedule
import com.triardn.kadesubmission.presenter.SchedulePresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.ScheduleView
import kotlinx.android.synthetic.main.activity_search.*
import kotlin.math.log

class SearchActivity: AppCompatActivity(), ScheduleView, View.OnClickListener {
    private lateinit var presenter: SchedulePresenter
    private var schedules: MutableList<Schedule> = mutableListOf()
    private lateinit var adapter: ScheduleAdapter
    private lateinit var searchView: SearchView

    var query: String? = "manchester united"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val actionbar = supportActionBar
        actionbar?.title = "Search Match"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        adapter = ScheduleAdapter(schedules)
        schedule_list.adapter = adapter
        schedule_list.layoutManager = LinearLayoutManager(this.applicationContext)

        btn_search.setOnClickListener(this)
        val query = search_query.text.toString()

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = SchedulePresenter(this, apiRepository, gson)
        presenter.searchMatch(query.replace(" ", "_"))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showLeagueMatches(data: List<Schedule>) {
        schedules.clear()
        schedules.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_search -> {
                println("clicked")
                query = search_query.text.toString()
                if (TextUtils.isEmpty(query)) {
                    return
                }

                presenter.searchMatch(query.toString().replace(" ", "_"))
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//
//        val searchMenu = menu.findItem(R.id.action_search_menu)
//        searchMenu.expandActionView()
//        searchView = searchMenu.actionView as SearchView
//        searchView.queryHint = "cari dulu dong"
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                presenter.searchMatch(query.toString().replace(" ", "_"))
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                presenter.searchMatch(newText.toString().replace(" ", "_"))
//                return true
//            }
//        })
//
//        return true
//    }
}