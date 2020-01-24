package com.triardn.kadesubmission.model

data class TeamFavorite(
    val idTeam: String?,
    val strTeam: String?,
    val strStadium: String?,
    val strStadiumThumb: String?,
    val strStadiumDescription: String?,
    val strStadiumLocation: String?,
    val intStadiumCapacity: String?,
    val strDescriptionEN: String?,
    val strTeamBadge: String?,
    val strTeamJersey: String
) {
    companion object {
        const val TABLE_TEAM_FAVORITE: String = "TABLE_TEAM_FAVORITE"
        const val ID_TEAM: String = "ID_TEAM"
        const val STR_TEAM: String = "STR_TEAM"
        const val STR_STADIUM: String = "STR_STADIUM"
        const val STR_STADIUM_THUMB: String = "STR_STADIUM_THUMB"
        const val STR_STADIUM_DESCRIPTION: String = "STR_STADIUM_DESCRIPTION"
        const val STR_STADIUM_LOCATION: String = "STR_STADIUM_LOCATION"
        const val INT_STADIUM_CAPACITY: String = "INT_STADIUM_CAPACITY"
        const val STR_DESCRIPTION_EN: String = "STR_DESCRIPTION_EN"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        const val STR_TEAM_JERSEY: String = "STR_TEAM_JERSEY"
    }
}