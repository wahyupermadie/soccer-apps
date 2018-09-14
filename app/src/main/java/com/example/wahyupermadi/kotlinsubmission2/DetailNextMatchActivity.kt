package com.example.wahyupermadi.kotlinsubmission2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.SurfaceHolder
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.wahyupermadi.kotlinsubmission2.api.ApiClient
import com.example.wahyupermadi.kotlinsubmission2.api.ApiInterface
import com.example.wahyupermadi.kotlinsubmission2.model.MatchsDetail
import com.example.wahyupermadi.kotlinsubmission2.model.NextMatchsDetail
import com.example.wahyupermadi.kotlinsubmission2.model.Team
import com.example.wahyupermadi.kotlinsubmission2.model.TeamResponse
import kotlinx.android.synthetic.main.activity_detail_nextmatch.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailNextMatchActivity : AppCompatActivity() {
    lateinit var toolbar: ActionBar
    lateinit var matchDetail: List<MatchsDetail>
    lateinit var awayTeamDetail: List<Team>
    lateinit var homeTeamDetail: List<Team>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_nextmatch)
        this.supportActionBar!!.title = "Match Detail"
        init()
    }

    private fun init() {

        val intent = intent
        val id = intent.getStringExtra("id")
        var apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.getDetailNextMatch(id)
        call.enqueue(object : Callback<NextMatchsDetail>{
            override fun onFailure(call: Call<NextMatchsDetail>, t: Throwable) {
                toast("error " +t)
            }

            override fun onResponse(call: Call<NextMatchsDetail>, response: Response<NextMatchsDetail>) {
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
        getImageHome(matchDetail.get(0).idHomeTeam)
        getImageAway(matchDetail.get(0).idAwayTeam)

    }

    private fun getImageAway(id: String) {
        var apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.getTeamDetail(id)
        call.enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                toast("error " +t)
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                awayTeamDetail = response.body()?.teams!!
                Glide.with(applicationContext).load(awayTeamDetail.get(0).strTeamLogo).into(nm_awayimgdetail)
            }

        })
    }

    private fun getImageHome(id: String) {
        var apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.getTeamDetail(id)
        call.enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                toast("error " +t)
            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                homeTeamDetail = response.body()?.teams!!
                Glide.with(applicationContext).load(homeTeamDetail.get(0).strTeamLogo).into(nm_imghomedetail)
            }

        })
    }
}
