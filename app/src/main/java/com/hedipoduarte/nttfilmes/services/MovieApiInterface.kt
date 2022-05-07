package com.hedipoduarte.nttfilmes.services

import com.google.gson.JsonObject
import com.hedipoduarte.nttfilmes.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiInterface {

    @GET("/3/trending/all/day?api_key=bc45447e5adf39e78fb05207c954760d&language=pt-Br")
    fun getMovieList(): Call<MovieResponse>

    @GET("/3/movie/{movie_id}?api_key=bc45447e5adf39e78fb05207c954760d&language=pt-Br")
    fun getMovieDetails(@Path("movie_id") movie_id: String): Call<JsonObject>
}