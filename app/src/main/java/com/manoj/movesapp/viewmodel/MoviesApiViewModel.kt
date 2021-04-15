package com.manoj.movesapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manoj.movesapp.model.request.MoviesSearchModel
import com.manoj.movesapp.model.response.ResponseMain
import com.manoj.movesapp.retrofit.MoviesApiInterface
import com.manoj.movesapp.retrofit.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call

/**
 * Created On : 12/4/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
class MoviesApiViewModel : ViewModel() {
    var servicesLiveData = MutableLiveData<ResponseMain>()
    var errorLiveData = MutableLiveData<String>()
    var callObject : Call<ResponseBody>? = null

    fun getMovies(searchText : String, apiKey : String)
            : LiveData<ResponseMain>{
        val moviesData = MoviesSearchModel(searchText, apiKey,
            RetrofitClient.retrofitClient.build().create(MoviesApiInterface::class.java))
        if(callObject!=null)
            callObject!!.cancel()
        callObject = moviesData.getServiceApi(
            {moviesData -> onSuccessData(moviesData)},
            {errorString -> onErrorResponse(errorString)})
        return servicesLiveData
    }

    private fun onErrorResponse(errorString: String){
        /**
         * Display Toast
         * */
        errorLiveData.value = errorString
    }

    private fun onSuccessData(moviesData: ResponseMain) {
        servicesLiveData.value = moviesData
    }

    fun getErrorData() : LiveData<String>{
        return errorLiveData
    }
}