package com.hedipoduarte.nttfilmes.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.hedipoduarte.nttfilmes.domain.model.Movie
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("results")
    val movies : List<Movie>

) : Parcelable {
    constructor() : this(mutableListOf())
}