package com.example.wahyupermadi.kotlinsubmission2.fragments

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.footballteam.adapter.FavoriteAdapter
import com.example.wahyupermadi.kotlinsubmission2.DetailMatchActivity
import com.example.wahyupermadi.kotlinsubmission2.R
import com.example.wahyupermadi.kotlinsubmission2.db.Favorite
import com.example.wahyupermadi.kotlinsubmission2.db.database
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FavoriteFragment : Fragment(){
    lateinit var favoriteAdapter: FavoriteAdapter
    private var favorites: MutableList<Favorite> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
            showFavorite()
    }

    private fun showFavorite() {
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            toast(""+favorite)
            favorites.addAll(favorite)
            rv_favorite.layoutManager = LinearLayoutManager(context)
            rv_favorite.adapter = FavoriteAdapter(context,favorites){
                startActivity<DetailMatchActivity>("id" to "${it.id_match}")
            }
        }
    }
    companion object {
        fun newInstance(): FavoriteFragment = FavoriteFragment()
    }
}