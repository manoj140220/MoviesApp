package com.manoj.movesapp.view.adapter.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manoj.movesapp.R

/**
 * Created On : 12/4/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
class MoviesViewHolder(viewGroup: ViewGroup, layout : Int) :
    RecyclerView.ViewHolder((LayoutInflater.from(viewGroup.context)
    .inflate(layout, viewGroup, false))) {

    private var movieThumbImage : ImageView? = null
    private var movieTitle : TextView? = null
    private var movieReleaseYear : TextView? = null

    init {
        movieThumbImage = itemView.findViewById(R.id.view_image)
        movieTitle = itemView.findViewById(R.id.movie_title_name)
        movieReleaseYear = itemView.findViewById(R.id.movie_released_year)
    }

    fun getMovieThumb() : ImageView{
        return movieThumbImage!!
    }

    fun getMovieTitle() : TextView{
        return movieTitle!!
    }

    fun getMovieReleaseYear() : TextView{
        return movieReleaseYear!!
    }
}