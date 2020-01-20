package com.triardn.kadesubmission.model

data class Favorite (
    val idEvent: String?,
    val strEvent: String?,
    val strDate: String?,
    val strTime: String?,
    val idHomeTeam: String?,
    val idAwayTeam: String?,
    val intHomeScore: String?,
    val intAwayScore: String?,
    val strHomeGoalDetails: String?,
    val strAwayGoalDetails: String?,
    val strHomeYellowCards: String?,
    val strAwayYellowCards: String?
) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID_EVENT: String = "ID_EVENT"
        const val STR_EVENT: String = "STR_EVENT"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val EVENT_TIME: String = "EVENT_TIME"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val HOME_TEAM_SCORE: String = "HOME_TEAM_SCORE"
        const val AWAY_TEAM_SCORE: String = "AWAY_TEAM_SCORE"
        const val HOME_TEAM_GOAL_DETAILS: String = "HOME_TEAM_GOAL_DETAILS"
        const val AWAY_TEAM_GOAL_DETAILS: String = "AWAY_TEAM_GOAL_DETAILS"
        const val HOME_TEAM_YELLOW_CARDS: String = "HOME_TEAM_YELLOW_CARDS"
        const val AWAY_TEAM_YELLOW_CARDS: String = "AWAY_TEAM_YELLOW_CARDS"
    }
}