package com.triardn.kadesubmission.repository

import android.net.Uri
import com.triardn.kadesubmission.BuildConfig


object TheSportsDBApi {
    fun getLeague(leagueID: Int?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupleague.php")
            .appendQueryParameter("id", leagueID.toString())
            .build()
            .toString()
    }
}