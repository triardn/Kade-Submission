package com.triardn.kadesubmission

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.triardn.kadesubmission.model.Favorite
import com.triardn.kadesubmission.model.Schedule
import com.triardn.kadesubmission.model.Team
import com.triardn.kadesubmission.presenter.MatchDetailPresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.MatchDetailView
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class DetailMatchActivity: AppCompatActivity(), MatchDetailView {
    private lateinit var matchDetailPresenter: MatchDetailPresenter
    private var match: Schedule? = null
    private lateinit var id: String
    private var isFavoriteFlag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.getBundleExtra("Bundle")
        val event = bundle?.getParcelable<Schedule>("match")

        isFavorite(event?.idEvent.orEmpty())

        val actionbar = supportActionBar
        actionbar?.title = "Match Detail"
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()

        if (event != null) {
            matchDetailPresenter = MatchDetailPresenter(this, request, gson)
            matchDetailPresenter.getTeamDetail(event)
        }
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
        match = matchDetail
        id = match?.idEvent.orEmpty()
        MatchDetailActivityUI(homeTeam, awayTeam, matchDetail).setContentView(this)
    }

    private var menuItem: Menu? = null

    private fun isFavorite(id: String) {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs(
                "(ID_EVENT = {idEvent})",
                "idEvent" to id
            )
            val favoriteMatch = result.parseList(classParser<Favorite>())

            if (favoriteMatch.isNotEmpty()) isFavoriteFlag = true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu

        if (isFavoriteFlag) {
            menu?.getItem(0)?.setIcon(R.drawable.ic_added_to_favorites)
        } else {
            menu?.getItem(0)?.setIcon(R.drawable.ic_add_to_favorites)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavoriteFlag) {
                    if(removeFromFavorite()) {
                        isFavoriteFlag = !isFavoriteFlag
                        setFavorite()
                    }
                } else {
                    if(addToFavorite()) {
                        isFavoriteFlag = !isFavoriteFlag
                        setFavorite()
                    }
                }

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(): Boolean {
        match?.let {
            try {
                database.use {
                    insert(Favorite.TABLE_FAVORITE,
                        Favorite.ID_EVENT to match?.idEvent,
                        Favorite.STR_EVENT to match?.strEvent,
                        Favorite.EVENT_DATE to match?.strDate,
                        Favorite.EVENT_TIME to match?.strTime,
                        Favorite.HOME_TEAM_ID to match?.idHomeTeam,
                        Favorite.AWAY_TEAM_ID to match?.idAwayTeam,
                        Favorite.HOME_TEAM_SCORE to match?.intHomeScore,
                        Favorite.AWAY_TEAM_SCORE to match?.intAwayScore,
                        Favorite.HOME_TEAM_GOAL_DETAILS to match?.strHomeGoalDetails,
                        Favorite.AWAY_TEAM_GOAL_DETAILS to match?.strAwayGoalDetails,
                        Favorite.HOME_TEAM_YELLOW_CARDS to match?.strHomeYellowCards,
                        Favorite.AWAY_TEAM_YELLOW_CARDS to match?.strAwayYellowCards
                    )
                }
                Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()

                return true
            } catch (e: SQLiteConstraintException){
                println(e.localizedMessage)
                return false
            }
        }

        Toast.makeText(this, "Wait until data loaded before you add it to favorite", Toast.LENGTH_SHORT).show()

        return false
    }

    private fun removeFromFavorite(): Boolean{
        match?.let {
            try {
                database.use {
                    delete(Favorite.TABLE_FAVORITE, "(ID_EVENT = {id})",
                        "id" to id)
                }
                Toast.makeText(this, "Removed to favorite", Toast.LENGTH_SHORT).show()

                return true
            } catch (e: SQLiteConstraintException){
                println(e.localizedMessage)
                return false
            }
        }

        Toast.makeText(this, "Wait until data loaded before you remove from favorite", Toast.LENGTH_SHORT).show()

        return false
    }

    private fun setFavorite() {
        if (isFavoriteFlag)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }


}