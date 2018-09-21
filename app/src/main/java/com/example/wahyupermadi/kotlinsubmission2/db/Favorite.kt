package com.example.wahyupermadi.kotlinsubmission2.db

data class Favorite(
        var id : Long?,
        var id_match : String?,
        var strAwayFormation : String?,
        var strHomeFormation : String?,
        var strHomeTeam : String?,
        var strAwayTeam : String?,
        var intHomeShots : String?,
        var intAwayShots : String?,
        var strHomeLineupGoalkeeper : String?,
        var strHomeLineupDefense: String?,
        var strHomeLineupMidfield: String?,
        var strHomeLineupForward: String?,
        var strHomeLineupSubstitutes: String?,
        var strAwayLineupGoalkeeper : String?,
        var strAwayLineupDefense: String?,
        var strAwayLineupMidfield: String?,
        var strAwayLineupForward: String?,
        var strAwayLineupSubstitutes: String?,
        var idHomeTeam : String?,
        var idAwayTeam : String?,
        var intAwayScore: String?,
        var intHomeScore: String?,
        var dateEvent : String?,
        var strAwayGoalDetails : String?,
        var strHomeGoalDetails : String?
) {
    companion object {
        const val TABLE_FAVORITE : String = "TABLE_FAVORITE"
        const val ID : String = "ID_FAVORITE"
        const val ID_MATCH : String = "ID_MATCH"
        const val AWAY_FORMATION : String = "AWAY_FORMATION"
        const val HOME_FORMATION : String = "HOME_FORMATION"
        const val AWAY_TEAM : String = "AWAY_TEAM"
        const val HOME_TEAM : String = "HOME_TEAM"
        const val HOME_SHOT : String = "AWAY_SHOT"
        const val AWAY_SHOT : String = "HOME_SHOT"
        const val HOME_GOALKEEPER : String = "HOME_GOALKEEPER"
        const val HOME_DEFF : String = "HOME_DEFF"
        const val HOME_MIDFIELD : String = "HOME_MIDFIELD"
        const val HOME_FORWARD : String = "HOME_FORWARD"
        const val HOME_SUBS : String = "HOME_SUBS"
        const val AWAY_GOALKEEPER : String = "AWAY_GOALKEEPER"
        const val AWAY_DEFF : String = "AWAY_DEFF"
        const val AWAY_MIDFIELD : String = "AWAY_MIDFIELD"
        const val AWAY_FORWARD : String = "AWAY_FORWARD"
        const val AWAY_SUBS : String = "AWAY_SUBS"
        const val HOME_ID : String = "HOME_ID"
        const val AWAY_ID  : String = "AWAY_ID"
        const val AWAY_SCORE : String = "HOME_SCORE"
        const val HOME_SCORE : String = "AWAY_SCORE"
        const val DATE_EVENT : String = "DATE_EVENT"
        const val AWAY_GOALDET : String = "AWAY_GOALDET"
        const val HOME_GOALDET  : String = "HOME_GOALDET"
    }
}