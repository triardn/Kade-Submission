package com.triardn.kadesubmission

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.triardn.kadesubmission.model.Favorite
import com.triardn.kadesubmission.model.TeamFavorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context) : MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }

            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // create database
        db.createTable(Favorite.TABLE_FAVORITE, true,
            Favorite.ID_EVENT to TEXT,
            Favorite.STR_EVENT to TEXT,
            Favorite.EVENT_DATE to TEXT,
            Favorite.EVENT_TIME to TEXT,
            Favorite.HOME_TEAM_ID to TEXT,
            Favorite.AWAY_TEAM_ID to TEXT,
            Favorite.HOME_TEAM_SCORE to TEXT,
            Favorite.AWAY_TEAM_SCORE to TEXT,
            Favorite.HOME_TEAM_GOAL_DETAILS to TEXT,
            Favorite.AWAY_TEAM_GOAL_DETAILS to TEXT,
            Favorite.HOME_TEAM_YELLOW_CARDS to TEXT,
            Favorite.AWAY_TEAM_YELLOW_CARDS to TEXT
        )

        db.createTable(TeamFavorite.TABLE_TEAM_FAVORITE, true,
            TeamFavorite.ID_TEAM to TEXT,
            TeamFavorite.STR_TEAM to TEXT,
            TeamFavorite.STR_STADIUM to TEXT,
            TeamFavorite.STR_STADIUM_THUMB to TEXT,
            TeamFavorite.STR_STADIUM_DESCRIPTION to TEXT,
            TeamFavorite.STR_STADIUM_LOCATION to TEXT,
            TeamFavorite.INT_STADIUM_CAPACITY to TEXT,
            TeamFavorite.STR_DESCRIPTION_EN to TEXT,
            TeamFavorite.STR_TEAM_BADGE to TEXT,
            TeamFavorite.STR_TEAM_JERSEY to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(TeamFavorite.TABLE_TEAM_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)