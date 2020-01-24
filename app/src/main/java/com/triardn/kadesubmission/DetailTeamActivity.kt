package com.triardn.kadesubmission

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.triardn.kadesubmission.model.Team
import com.triardn.kadesubmission.presenter.TeamDetailPresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.TeamDetailView
import org.jetbrains.anko.*

class DetailTeamActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var teamDetailPresenter: TeamDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent.getBundleExtra("Bundle")
        val team = intent?.getParcelable<Team>("team")

        val actionBar = supportActionBar
        actionBar?.title = team?.strTeam
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        teamDetailPresenter = TeamDetailPresenter(this, request, gson)
        teamDetailPresenter.getTeamDetail(team?.idTeam?.toInt())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun getTeamDetail(data: Team) {
        DetailTeamUI(data).setContentView(this)
    }

    class DetailTeamUI(var item: Team) : AnkoComponent<DetailTeamActivity> {
        override fun createView(ui: AnkoContext<DetailTeamActivity>) = with(ui) {
            scrollView {
                verticalLayout {
                    val img = imageView().lparams(height = 500, width = 500) {
                        padding = dip(20)
                        margin = dip(15)
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    Glide.with(this.context)
                        .load(item.strTeamBadge)
                        .override(300, 300)
                        .into(img)

                    textView {
                        text = item.strTeam
                        textSize = sp(10).toFloat()
                        gravity = Gravity.CENTER_HORIZONTAL
                        padding = dip(10)
                    }

                    textView {
                        text = item.strDescriptionEN
                        padding = dip(10)
                    }
                }
            }
        }
    }
}