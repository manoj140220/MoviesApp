package com.manoj.movesapp.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created On : 12/4/21
 * Author     : Manoj Basavaraja
 * Name       : Manoj DB
 */
class SearchDataModel {
    @SerializedName("Title")
    val title : String? = null

    @SerializedName("Year")
    val year : String? = null

    @SerializedName("Poster")
    val postImage : String? = null
}