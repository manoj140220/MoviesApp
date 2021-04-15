package com.manoj.movesapp.retrofit

import androidx.lifecycle.MutableLiveData
import okhttp3.ResponseBody
import retrofit2.Call
import java.lang.reflect.Type

/**
 * Created On : 1/3/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
abstract class ApiService<T,S> {
    protected abstract fun getService(): T
    protected abstract fun getCall(service: T): Call<ResponseBody>
    protected abstract fun getClassType(): Type?

    open fun getServiceApi(success : (S) -> Unit, error : (String) -> Unit) : Call<ResponseBody>{
        val call : Call<ResponseBody> = getCall(getService())
        call.enqueue(object : ApiCallBack<S>(getClassType()){
            override fun onSuccess(response: S) {
                success(response)
            }

            override fun onError(response: String?) {
                error(response!!)
            }
        })
        return call
    }
}