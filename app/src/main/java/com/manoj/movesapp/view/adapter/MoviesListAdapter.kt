package com.manoj.movesapp.view.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manoj.movesapp.R
import com.manoj.movesapp.model.response.ResponseMain
import com.manoj.movesapp.view.adapter.viewHolder.MoviesViewHolder

/**
 * Created On : 12/4/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
class MoviesListAdapter(private val responseMain : ResponseMain?,
                        private val context : Context) : RecyclerView.Adapter<MoviesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(parent, R.layout.list_item)
    }

    override fun getItemCount(): Int {
        if(responseMain?.search != null){
            return responseMain.search!!.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.getMovieTitle().text = "Name : ${responseMain!!.search!![position].title}"
        holder.getMovieReleaseYear().text = "Year : ${responseMain!!.search!![position].year}"

        Glide.with(context)
            .load(responseMain!!.search!![position].postImage)
            .into(holder.getMovieThumb())
    }
}