package com.manoj.movesapp.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created On : 12/4/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
class ResponseMain {
    @SerializedName("Search")
    val search : MutableList<SearchDataModel>? = null

    @SerializedName("totalResults")
    val totalResult : String? = null

    @SerializedName("Response")
    val response : String? = null
}