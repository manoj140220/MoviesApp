package com.manoj.movesapp.model.request

import com.manoj.movesapp.model.response.ResponseMain
import com.manoj.movesapp.retrofit.ApiService
import com.manoj.movesapp.retrofit.MoviesApiInterface
import okhttp3.ResponseBody
import retrofit2.Call
import java.lang.reflect.Type

/**
 * Created On : 12/4/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
class MoviesSearchModel(private val searchTurn : String,
                        private val apiKey : String,
                        private val moviesApiInterface: MoviesApiInterface)
    : ApiService<MoviesApiInterface, ResponseMain>(){
    override fun getService(): MoviesApiInterface {
        return moviesApiInterface
    }

    override fun getCall(service: MoviesApiInterface): Call<ResponseBody> {
        return service.getMoviesList(searchTurn, apiKey)
    }

    override fun getClassType(): Type? {
        return ResponseMain::class.java
    }
}