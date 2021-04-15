package com.manoj.movesapp.retrofit

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

/**
 * Created On : 1/3/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
abstract class ApiCallBack<T>(private val type : Type?) : Callback<ResponseBody> {
    abstract fun onSuccess(response : T)
    abstract fun onError(response : String?)

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        var responseData : String ?= null
        if(call.isExecuted && response.isSuccessful){
            responseData = response.body()!!.string()
            val successResponse : T = Gson().fromJson(responseData, type)
            onSuccess(successResponse)
        }
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        val error = if (t.localizedMessage != null) t.localizedMessage else t.message!!
        onError(error)
    }
}