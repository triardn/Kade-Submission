package com.triardn.kadesubmission.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.triardn.kadesubmission.MatchScheduleActivity
import com.triardn.kadesubmission.R
import com.triardn.kadesubmission.adapter.ScheduleAdapter
import com.triardn.kadesubmission.model.Schedule
import com.triardn.kadesubmission.presenter.SchedulePresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.ScheduleView
import kotlinx.android.synthetic.main.fragment_previous_match.*

class NextMatchFragment : Fragment(), ScheduleView {
    private var schedules: MutableList<Schedule> = mutableListOf()
    private lateinit var presenter: SchedulePresenter
    private lateinit var adapter: ScheduleAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ScheduleAdapter(schedules)
        schedule_list.adapter = adapter
        schedule_list.layoutManager = LinearLayoutManager(this.context)

        val request = ApiRepository()
        val gson = Gson()
        presenter = SchedulePresenter(this, request, gson)
        presenter.getNextMatches((activity as MatchScheduleActivity?)?.leagueID?.toInt())
    }

    override fun showLeagueMatches(data: List<Schedule>) {
        schedules.clear()
        schedules.addAll(data)
        adapter.notifyDataSetChanged()
    }
}