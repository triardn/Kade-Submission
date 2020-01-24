package com.triardn.kadesubmission

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.triardn.kadesubmission.model.Favorite
import com.triardn.kadesubmission.model.Schedule
import com.triardn.kadesubmission.model.Team
import com.triardn.kadesubmission.model.TeamFavorite
import com.triardn.kadesubmission.presenter.TeamDetailPresenter
import com.triardn.kadesubmission.repository.ApiRepository
import com.triardn.kadesubmission.view.TeamDetailView
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamActivity : AppCompatActivity(), TeamDetailView {
    private lateinit var teamDetailPresenter: TeamDetailPresenter
    private var isFavoriteFlag: Boolean = false
    private var team: Team? = null
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent.getBundleExtra("Bundle")
        val team = intent?.getParcelable<Team>("team")

        isFavorite(team?.idTeam.orEmpty())

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
        team = data
        id = data.idTeam.orEmpty()
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

    private fun isFavorite(id: String) {
        database.use {
            val result = select(TeamFavorite.TABLE_TEAM_FAVORITE).whereArgs(
                "(ID_TEAM = {idTeam})",
                "idTeam" to id
            )
            val favoriteTeam = result.parseList(classParser<TeamFavorite>())

            if (favoriteTeam.isNotEmpty()) isFavoriteFlag = true
        }

    }

    private var menuItem: Menu? = null

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
        team?.let {
            try {
                database.use {
                    insert(TeamFavorite.TABLE_TEAM_FAVORITE,
                        TeamFavorite.ID_TEAM to team?.idTeam,
                        TeamFavorite.STR_TEAM to team?.strTeam,
                        TeamFavorite.STR_STADIUM to team?.strStadium,
                        TeamFavorite.STR_STADIUM_THUMB to team?.strStadiumThumb,
                        TeamFavorite.STR_STADIUM_DESCRIPTION to team?.strStadiumDescription,
                        TeamFavorite.STR_STADIUM_LOCATION to team?.strStadiumLocation,
                        TeamFavorite.INT_STADIUM_CAPACITY to team?.intStadiumCapacity,
                        TeamFavorite.STR_DESCRIPTION_EN to team?.strDescriptionEN,
                        TeamFavorite.STR_TEAM_BADGE to team?.strTeamBadge,
                        TeamFavorite.STR_TEAM_JERSEY to team?.strTeamBadge
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
        team?.let {
            try {
                database.use {
                    delete(TeamFavorite.TABLE_TEAM_FAVORITE, "(ID_TEAM = {id})",
                        "id" to id)
                }
                Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()

                return true
            } catch (e: SQLiteConstraintException){
                println(e.localizedMessage)
                return false
            }
        }

        Toast.makeText(this, "Wait until data loaded before you remove it from favorite", Toast.LENGTH_SHORT).show()

        return false
    }

    private fun setFavorite() {
        if (isFavoriteFlag)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}