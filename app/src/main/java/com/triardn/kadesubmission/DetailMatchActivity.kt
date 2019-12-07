package com.triardn.kadesubmission

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.triardn.kadesubmission.model.Schedule
import com.triardn.kadesubmission.model.Team
import com.triardn.kadesubmission.presenter.MatchDetailPresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.MatchDetailView
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.custom.style

class DetailMatchActivity: AppCompatActivity(), MatchDetailView {
    private lateinit var matchDetailPresenter: MatchDetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionbar = supportActionBar
        actionbar?.title = "Match Detail"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()

        val bundle = intent.getBundleExtra("Bundle")
        val event = bundle?.getParcelable<Schedule>("match")

        matchDetailPresenter = MatchDetailPresenter(this, request, gson)
        matchDetailPresenter.getTeamDetail(event!!)
    }

    class MatchDetailActivityUI(var homeTeam: Team, var awayTeam: Team, var match: Schedule) : AnkoComponent<DetailMatchActivity> {
        override fun createView(ui: AnkoContext<DetailMatchActivity>) = with(ui) {
            scrollView {
                // Match title
                linearLayout {
                    lparams(width = matchParent, height = matchParent)
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER

                    textView {
                        textSize = 20f
                        text = "Match Detail"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textSize = sp(10).toFloat()
                        gravity = Gravity.CENTER_HORIZONTAL
                        padding = dip(10)
                    }.lparams {
                        marginEnd = dip(8)
                    }

                    // Team badge
                    constraintLayout {
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(8)
                        id = R.id.badge_section

                        val homeImg = imageView().lparams(height = 300, width = 300) {
                            padding = dip(20)
                            margin = dip(15)
                            topToTop = R.id.badge_section
                            endToStart = R.id.versus
                            marginEnd = dip(8)
                        }

                        Glide.with(this.context)
                            .load(homeTeam.strTeamBadge)
                            .override(300, 300)
                            .into(homeImg)

                        textView {
                            id = R.id.versus
                            textSize = 14f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }.lparams {
                            topToTop = R.id.badge_section
                            startToStart = R.id.badge_section
                            endToEnd = R.id.badge_section
                        }

                        val awayImg = imageView().lparams(height = 300, width = 300) {
                            padding = dip(20)
                            margin = dip(15)
                            topToTop = R.id.badge_section
                            startToEnd = R.id.versus
                            marginStart = dip(8)
                        }

                        Glide.with(this.context)
                            .load(awayTeam.strTeamBadge)
                            .override(300, 300)
                            .into(awayImg)
                    }

                    tableLayout {
                        lparams{
                            width = matchParent
                            height = matchParent
                            marginStart = dip(8)
                            marginEnd = dip(8)
                            rightMargin = dip(8)
                            leftMargin = dip(8)
                            topMargin = dip(16)
                            leftPadding = dip(50)
                        }

                        // Team name
                        tableRow {
                            weightSum = dip(3).toFloat()
                            lparams {
                                bottomMargin = dip(8)
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = homeTeam.strTeam
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = "Team"
                                gravity = Gravity.CENTER_HORIZONTAL
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = awayTeam.strTeam
                                gravity = Gravity.END
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }
                        }

                        // Match score
                        tableRow {
                            weightSum = dip(3).toFloat()
                            lparams {
                                bottomMargin = dip(8)
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = match.intHomeScore
                                // gravity = Gravity.CENTER_HORIZONTAL
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = "Score"
                                gravity = Gravity.CENTER_HORIZONTAL
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = match.intAwayScore
                                gravity = Gravity.END
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }
                        }

                        // Goal detail
                        tableRow {
                            weightSum = dip(3).toFloat()
                            lparams {
                                topMargin = dip(35)
                                bottomMargin = dip(35)
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = match.strHomeGoalDetails
                                // gravity = Gravity.CENTER_HORIZONTAL
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = "Goal Detail"
                                gravity = Gravity.CENTER_HORIZONTAL
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = match.strAwayGoalDetails
                                gravity = Gravity.END
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }
                        }

                        // Yellow cards
                        tableRow {
                            weightSum = dip(3).toFloat()
                            lparams {
                                topMargin = dip(35)
                                bottomMargin = dip(35)
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = match.strHomeYellowCards
                                // gravity = Gravity.CENTER_HORIZONTAL
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = "Y.C"
                                gravity = Gravity.CENTER_HORIZONTAL
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                text = match.strAwayYellowCards
                                gravity = Gravity.END
                            }.lparams{
                                width = dip(0)
                                height = wrapContent
                                weight = dip(1).toFloat()
                            }
                        }
                    }
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun getMatchDetail(matchDetail: Schedule, homeTeam: Team, awayTeam: Team) {
        MatchDetailActivityUI(homeTeam, awayTeam, matchDetail).setContentView(this)
    }
}