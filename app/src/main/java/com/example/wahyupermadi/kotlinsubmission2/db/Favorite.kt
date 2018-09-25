package com.example.wahyupermadi.kotlinsubmission2.db

data class Favorite(
        val id : Long?,
        val id_match : String?,
        val strHomeTeam : String?,
        val strAwayTeam : String?,
        val dateEvent : String?,
        val intAwayScore : String?,
        val intHomeScore  : String?
) {
    companion object {
        const val TABLE_FAVORITE : String = "TABLE_FAVORITE"
        const val ID : String = "ID_FAVORITE"
        const val ID_MATCH : String = "ID_MATCH"
        const val AWAY_TEAM : String = "AWAY_TEAM"
        const val HOME_TEAM : String = "HOME_TEAM"
        const val EVENT_DATE : String = "EVENT_DATE"
        const val AWAY_SCORE  : String = "AWAY_SCORE"
        const val HOME_SCORE  : String = "HOME_SCORE"
    }
}