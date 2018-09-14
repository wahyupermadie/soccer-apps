package com.example.wahyupermadi.kotlinsubmission2.fragments

import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.kotlinsubmission2.DetailNextMatchActivity
import com.example.wahyupermadi.kotlinsubmission2.R
import com.example.wahyupermadi.kotlinsubmission2.adapter.NextMatchAdapter
import com.example.wahyupermadi.kotlinsubmission2.api.ApiClient
import com.example.wahyupermadi.kotlinsubmission2.api.ApiInterface
import com.example.wahyupermadi.kotlinsubmission2.model.Matchs
import com.example.wahyupermadi.kotlinsubmission2.model.NextMatchs
import com.example.wahyupermadi.kotlinsubmission2.model.NextMatchsDetail
import kotlinx.android.synthetic.main.nextmatch_list.*
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

class NextMatchFragment : Fragment(){
    lateinit var nextMatchAdapter : NextMatchAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_nextmatch, container, false)
        recyclerView = rootView.findViewById(R.id.rv_nextmatch) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)

        getMatch()
        return rootView

    }

    private fun getMatch() {
        val progressBar = indeterminateProgressDialog("Hello! Please wait...")
        progressBar.show()

        var apiServices = ApiClient.client.create(ApiInterface::class.java)
        val call = apiServices.getNextMatch()
        call.enqueue(object : Callback<NextMatchs>{
            override fun onFailure(call: Call<NextMatchs>, t: Throwable) {
                toast("error "+t)
                progressBar.hide()
            }

            override fun onResponse(call: Call<NextMatchs>, response: Response<NextMatchs>) {
                var matchList: List<Matchs>? = response.body()?.events!!
                nextMatchAdapter = NextMatchAdapter(activity!!.applicationContext, matchList){
                    getDetail(it.idEvent)
                }
                recyclerView.setAdapter(nextMatchAdapter)

                progressBar.hide()

            }

        })
    }

    private fun getDetail(idEvent: String) {
        startActivity<DetailNextMatchActivity>("id" to "${idEvent}")
    }

    companion object {
        fun newInstance(): NextMatchFragment = NextMatchFragment()
    }
}