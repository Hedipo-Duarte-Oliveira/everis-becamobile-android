package com.hedipoduarte.nttfilmes.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: Int ?,

    @SerializedName("original_title")
    val title: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("release_date")
    val release: String?,

    @SerializedName("first_air_date")
    val first_air_date: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("vote_average")
    val vote_average: Double?

) : Parcelable {
    constructor() : this(0, "", "", "", "", "", "", 0.0)
}
