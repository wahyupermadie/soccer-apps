package com.example.wahyupermadi.kotlinsubmission2.api

import com.example.wahyupermadi.kotlinsubmission2.model.NextMatchs
import com.example.wahyupermadi.kotlinsubmission2.model.NextMatchsDetail
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("eventsnextleague.php?id=4328")
    fun getNextMatch(): Call<NextMatchs>

    @GET("lookupevent.php")
    fun getDetailNextMatch(@Query("id") id: String): Call<NextMatchsDetail>
}