package com.triardn.kadesubmission

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import com.google.gson.Gson
import com.triardn.kadesubmission.model.Item
import com.triardn.kadesubmission.model.League
import com.triardn.kadesubmission.presenter.DetailPresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.DetailView
import org.jetbrains.anko.*
import com.bumptech.glide.Glide

class DetailLeagueActivity : AppCompatActivity(), DetailView {
    private lateinit var leagueDetailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val league = intent.getParcelableExtra<Item>("league")

        val actionbar = supportActionBar
        actionbar?.title = league?.name
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        leagueDetailPresenter = DetailPresenter(this, request, gson)

        leagueDetailPresenter.getDetailLeague(league?.id)
    }

    class DetailLeagueActivityUI(var item: League) : AnkoComponent<DetailLeagueActivity> {
        override fun createView(ui: AnkoContext<DetailLeagueActivity>) = with(ui) {
            scrollView {
                verticalLayout {

                    val img = imageView().lparams(height = 500, width = 500) {
                        padding = dip(20)
                        margin = dip(15)
                        gravity = Gravity.CENTER_HORIZONTAL
                    }

                    Glide.with(this.context)
                        .load(item.strBadge)
                        .override(300, 300)
                        .into(img)

                    textView {
                        text = item.strLeague
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun getDetailLeague(data: League) {
        DetailLeagueActivityUI(data).setContentView(this)
    }
}