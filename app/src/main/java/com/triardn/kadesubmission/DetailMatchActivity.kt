package com.triardn.kadesubmission

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.triardn.kadesubmission.model.Schedule
import com.triardn.kadesubmission.presenter.MatchDetailPresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.MatchDetailView
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class DetailMatchActivity: AppCompatActivity(), MatchDetailView {
    private lateinit var matchDetailPresenter: MatchDetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionbar = supportActionBar
        actionbar?.title = "Match Detail"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()

        matchDetailPresenter = MatchDetailPresenter(this, request, gson)
        intent?.getStringExtra("matchId")?.let { matchDetailPresenter.getMatchDetail(it) }
    }

    class MatchDetailActivityUI(var item: Schedule) : AnkoComponent<DetailMatchActivity> {
        override fun createView(ui: AnkoContext<DetailMatchActivity>) = with(ui) {
            scrollView {
                verticalLayout {
                    textView {
                        text = item.strEvent
                        textSize = sp(10).toFloat()
                        gravity = Gravity.CENTER_HORIZONTAL
                        padding = dip(10)
                    }
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun getMatchDetail(data: Schedule) {
        MatchDetailActivityUI(data).setContentView(this)
    }
}