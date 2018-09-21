package com.example.wahyupermadi.kotlinsubmission2

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBar
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.wahyupermadi.kotlinsubmission2.R.drawable.ic_add_favorite
import com.example.wahyupermadi.kotlinsubmission2.R.drawable.ic_added_favorite
import com.example.wahyupermadi.kotlinsubmission2.api.ApiClient
import com.example.wahyupermadi.kotlinsubmission2.api.ApiInterface
import com.example.wahyupermadi.kotlinsubmission2.model.MatchsDetail
import com.example.wahyupermadi.kotlinsubmission2.model.DetailMatchsDetail
import com.example.wahyupermadi.kotlinsubmission2.model.Team
import com.example.wahyupermadi.kotlinsubmission2.R.menu.favorite_detail
import com.example.wahyupermadi.kotlinsubmission2.R.id.add_to_favorite
import com.example.wahyupermadi.kotlinsubmission2.db.DBHelper
import com.example.wahyupermadi.kotlinsubmission2.db.Favorite
import com.example.wahyupermadi.kotlinsubmission2.db.database
import com.example.wahyupermadi.kotlinsubmission2.model.TeamResponse
import kotlinx.android.synthetic.main.activity_detail_nextmatch.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMatchActivity : AppCompatActivity() {
    lateinit var toolbar: ActionBar
    lateinit var matchDetail: List<MatchsDetail>
    lateinit var awayTeamDetail: List<Team>
    lateinit var homeTeamDetail: List<Team>
    private var menuItem: Menu? = null
    private var isFavorite : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_nextmatch)
        this.supportActionBar!!.title = "Match Detail"
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(favorite_detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId){
            android.R.id.home -> {
                finish()
                true
            }

            add_to_favorite -> {
                if (isFavorite) deleteFavorite() else addFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_favorite)
        }
        else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_favorite)
        }
    }

    private fun deleteFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(ID_MATCH = {id})",
                        "id" to matchDetail.get(0).idEvent)
            }
            toast("deleted favorite")
        }catch (e: SQLiteConstraintException){
            toast(""+e.localizedMessage)
        }
    }

    private fun addFavorite() {
//        toast(""+matchDetail.get(0).strHomeTeam)
        val away  = matchDetail.get(0).strAwayTeam
//        toast(away)
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.ID_MATCH to matchDetail.get(0).idEvent,
                        Favorite.DATE_EVENT to matchDetail.get(0).dateEvent,
                        Favorite.AWAY_DEFF to matchDetail.get(0).strAwayLineupDefense,
                        Favorite.HOME_DEFF to matchDetail.get(0).strHomeLineupDefense,
                        Favorite.HOME_SHOT to matchDetail.get(0).intHomeShots,
                        Favorite.AWAY_SHOT to matchDetail.get(0).intAwayShots,
                        Favorite.AWAY_TEAM to matchDetail.get(0).strAwayTeam,
                        Favorite.HOME_TEAM to matchDetail.get(0).strHomeTeam,
                        Favorite.HOME_SCORE to matchDetail.get(0).intHomeScore,
                        Favorite.AWAY_SCORE to matchDetail.get(0).intAwayScore,
                        Favorite.AWAY_ID to matchDetail.get(0).idAwayTeam,
                        Favorite.HOME_ID to matchDetail.get(0).idHomeTeam,
                        Favorite.HOME_MIDFIELD to matchDetail.get(0).strHomeLineupMidfield,
                        Favorite.AWAY_MIDFIELD to matchDetail.get(0).strAwayLineupMidfield,
                        Favorite.HOME_GOALKEEPER to matchDetail.get(0).strHomeLineupGoalkeeper,
                        Favorite.AWAY_GOALKEEPER to matchDetail.get(0).strAwayLineupGoalkeeper,
                        Favorite.HOME_GOALDET to matchDetail.get(0).strHomeGoalDetails,
                        Favorite.AWAY_GOALDET to matchDetail.get(0).strAwayGoalDetails,
                        Favorite.HOME_FORWARD to matchDetail.get(0).strHomeLineupForward,
                        Favorite.AWAY_FORWARD to matchDetail.get(0).strAwayLineupForward,
                        Favorite.HOME_FORMATION to matchDetail.get(0).strHomeFormation,
                        Favorite.AWAY_FORMATION to matchDetail.get(0).strAwayFormation,
                        Favorite.HOME_SUBS to matchDetail.get(0).strHomeLineupSubstitutes,
                        Favorite.AWAY_SUBS to matchDetail.get(0).strAwayLineupSubstitutes
                )
            }
            toast("added favorite")
        }catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun favoriteState(id : String){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(ID_MATCH = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            toast(""+favorite)
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun init() {

        val intent = intent
        val id = intent.getStringExtra("id")
        favoriteState(id)
        val apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.getDetailNextMatch(id)
        call.enqueue(object : Callback<DetailMatchsDetail>{
            override fun onFailure(call: Call<DetailMatchsDetail>, t: Throwable) {
                toast("error " +t)
            }

            override fun onResponse(call: Call<DetailMatchsDetail>, response: Response<DetailMatchsDetail>) {
                matchDetail = response.body()?.events!!
                setData()
            }

        })
    }
    private fun setData() {
        nm_detaildate.text = matchDetail.get(0).dateEvent
        nm_hometeamdetail.text = matchDetail.get(0).strHomeTeam
        nm_awayteamdetail.text = matchDetail.get(0).strAwayTeam
        nm_homescoredetail.text = matchDetail.get(0).intHomeScore
        nm_awayteamscoredetail.text = matchDetail.get(0).intAwayScore
        shotsHomeTv.text = matchDetail.get(0).intHomeShots
        shotsAwayTv.text = matchDetail.get(0).intAwayShots
        gkHomeTv.text = matchDetail.get(0).strHomeLineupGoalkeeper
        gkAwayTv.text = matchDetail.get(0).strAwayLineupGoalkeeper
        mfHomeTv.text = matchDetail.get(0).strHomeLineupMidfield
        mfAwayTv.text = matchDetail.get(0).strAwayLineupMidfield
        deffHomeTv.text = matchDetail.get(0).strHomeLineupDefense
        deffHomeTv.text = matchDetail.get(0).strAwayLineupDefense
        fwHomeTv.text = matchDetail.get(0).strHomeLineupForward
        fwAwayTv.text = matchDetail.get(0).strAwayLineupForward
        subHomeTv.text = matchDetail.get(0).strHomeLineupSubstitutes
        subAwayTv.text = matchDetail.get(0).strAwayLineupSubstitutes
        goalsHomeTv.text = matchDetail.get(0).strHomeGoalDetails
        goalsAwayTv.text = matchDetail.get(0).strAwayGoalDetails
        nm_homeformation.text = matchDetail.get(0).strHomeFormation
        nm_awayformation.text = matchDetail.get(0).strAwayFormation
        getImageHome(matchDetail.get(0).idHomeTeam)
        getImageAway(matchDetail.get(0).idAwayTeam)

    }

    private fun getImageAway(id: String) {
        val apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.getTeamDetail(id)
        call.enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                toast("error " +t)
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                val body = response.body()
                awayTeamDetail = if (body != null) body.teams else emptyList()
                Glide.with(applicationContext).load(awayTeamDetail.get(0).strTeamLogo).into(nm_awayimgdetail)
            }

        })
    }

    private fun getImageHome(id: String) {
        val apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.getTeamDetail(id)
        call.enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                toast("error " +t)
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                val body = response.body()
                homeTeamDetail = if (body != null) body.teams else emptyList()
                Glide.with(applicationContext).load(homeTeamDetail.get(0).strTeamLogo).into(nm_imghomedetail)
            }

        })
    }
}
