package com.triardn.kadesubmission.repository

import android.net.Uri
import com.triardn.kadesubmission.BuildConfig

object TheSportsDBApi {
    fun getLeague(leagueID: Int?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupleague.php?id=" + leagueID.toString()
    }

    fun getPreviousMatches(leagueID: Int?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id=" + leagueID.toString()
    }

    fun getNextMatches(leagueID: Int?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id=" + leagueID.toString()
    }

    fun getMatchDetail(matchID: String): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupevent.php")
            .appendQueryParameter("id", matchID)
            .build()
            .toString()
    }

    fun getTeamDetail(teamID: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=" + teamID
    }

    fun searchMatch(query: String): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" + query
    }
}