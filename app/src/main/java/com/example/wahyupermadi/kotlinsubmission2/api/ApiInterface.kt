package com.example.wahyupermadi.kotlinsubmission2.api

import com.example.wahyupermadi.kotlinsubmission2.model.NextMatchs
import com.example.wahyupermadi.kotlinsubmission2.model.NextMatchsDetail
import com.example.wahyupermadi.kotlinsubmission2.model.TeamResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface ApiInterface {
    @GET("eventsnextleague.php?id=4328")
    fun getNextMatch(): Call<NextMatchs>

    @GET("lookupevent.php")
    fun getDetailNextMatch(@Query("id") id: String): Call<NextMatchsDetail>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") id: String) : Call<TeamResponse>
}