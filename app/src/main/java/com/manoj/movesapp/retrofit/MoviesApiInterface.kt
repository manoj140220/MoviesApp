package com.manoj.movesapp.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created On : 12/4/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
interface MoviesApiInterface {
    @GET("/")
    fun getMoviesList(@Query("s") search : String,
                      @Query("apikey") apiKey : String) : Call<ResponseBody>
}