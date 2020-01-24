package com.triardn.kadesubmission

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.triardn.kadesubmission.adapter.ScheduleAdapter
import com.triardn.kadesubmission.model.Schedule
import com.triardn.kadesubmission.presenter.SchedulePresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.ScheduleView
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity: AppCompatActivity(), ScheduleView, View.OnClickListener {
    private lateinit var presenter: SchedulePresenter
    private var schedules: MutableList<Schedule> = mutableListOf()
    private lateinit var adapter: ScheduleAdapter

    var query: String? = ""

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

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = SchedulePresenter(this, apiRepository, gson)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showLeagueMatches(data: List<Schedule>) {
        schedules.clear()

        if (data.isNotEmpty()) {
            schedules.addAll(data)
        } else {
            Toast.makeText(this, "Match schedule not found", Toast.LENGTH_SHORT).show()
        }

        adapter.notifyDataSetChanged()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_search -> {
                query = search_query.text.toString()
                if (TextUtils.isEmpty(query)) {
                    return
                }

                presenter.searchMatch(query.toString().replace(" ", "_"))
            }
        }
    }
}