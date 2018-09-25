package com.example.wahyupermadi.footballteam.adapter

import android.content.Context
import android.support.constraint.Constraints.TAG
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.wahyupermadi.kotlinsubmission2.R
import com.example.wahyupermadi.kotlinsubmission2.db.Favorite
import kotlinx.android.synthetic.main.favorite_list.view.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteAdapter (private val context: Context?, private val favorite: List<Favorite>?, private val listener: (Favorite) -> Unit) : RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite!![position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.favorite_list, parent, false))


    override fun getItemCount(): Int {
        return favorite!!.size
    }
}

class FavoriteViewHolder(view : View?) : RecyclerView.ViewHolder(view){
    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        itemView.tv_hometeam.text = favorite.strHomeTeam
        itemView.tv_date.text = favorite.dateEvent
        itemView.tv_awayteam.text = favorite.strAwayTeam
        itemView.tv_awayscore.text = favorite.intAwayScore
        itemView.tv_homescore.text = favorite.intHomeScore
        itemView.setOnClickListener{
            listener(favorite)
        }
    }
}
