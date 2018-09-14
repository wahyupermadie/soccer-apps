package com.example.wahyupermadi.kotlinsubmission2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.widget.Toast
import com.example.wahyupermadi.kotlinsubmission2.api.ApiClient
import com.example.wahyupermadi.kotlinsubmission2.api.ApiInterface
import com.example.wahyupermadi.kotlinsubmission2.model.MatchsDetail
import com.example.wahyupermadi.kotlinsubmission2.model.NextMatchsDetail
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailNextMatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        setData()
    }

    private fun setData() {
        val intent = intent
        val id = intent.getStringExtra("id")
        var apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.getDetailNextMatch(id)
        call.enqueue(object : Callback<NextMatchsDetail>{
            override fun onFailure(call: Call<NextMatchsDetail>, t: Throwable) {
                toast("error " +t)
            }

            override fun onResponse(call: Call<NextMatchsDetail>, response: Response<NextMatchsDetail>) {
                var matchDetail: List<MatchsDetail>? = response.body()?.events!!
            }

        })
    }
}
