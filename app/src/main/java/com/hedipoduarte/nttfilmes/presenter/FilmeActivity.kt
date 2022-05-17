package com.hedipoduarte.nttfilmes.presenter

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.hedipoduarte.nttfilmes.R
import com.hedipoduarte.nttfilmes.data.api.MovieApiInterface
import com.hedipoduarte.nttfilmes.domain.MovieApiService
import com.hedipoduarte.nttfilmes.domain.model.Movie
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class FilmeActivity : AppCompatActivity() {

    private var selectedMovie: Movie? = null
    private lateinit var movieId: String

    private lateinit var movie_title_filme: TextView
    private lateinit var movie_overview_filme: TextView
    private lateinit var movie_class_filme: TextView
    private lateinit var movie_imageView_filme: ImageView
    private lateinit var ratingBar: RatingBar
    val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filme)

        selectedMovie = intent.getParcelableExtra("movies")
        movieId = selectedMovie!!.id.toString()
        Log.d("movieDet", movieId)

        movie_title_filme = findViewById(R.id.movie_title_filme)
        movie_overview_filme = findViewById(R.id.movie_overview_filme)
        movie_class_filme = findViewById(R.id.movie_class_filme)
        movie_imageView_filme = findViewById(R.id.movie_imageView_filme)
        ratingBar = findViewById(R.id.ratingBar)
        getMovieDetails()
    }

    private fun getMovieDetails() {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieDetails(movieId).enqueue(object : retrofit2.Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("movieDet", response.body().toString())

                try {
                    val jsonObject = JSONObject(Gson().toJson(response.body()))

                    movie_title_filme.text = jsonObject.getString("original_title")
                    if (jsonObject.getString("overview") == "") {
                        movie_overview_filme.text = selectedMovie?.overview
                    } else {
                        movie_overview_filme.text = jsonObject.getString("overview")
                    }
                    movie_class_filme.text = jsonObject.getString("vote_average")
                    ratingBar.rating = (jsonObject.getDouble("vote_average") / 2).toFloat()
                    Glide.with(this@FilmeActivity)
                        .load(IMAGE_BASE + jsonObject.getString("poster_path"))
                        .into(movie_imageView_filme)
                } catch (e: Exception) {
                    loadInfo()
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(this@FilmeActivity, t.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun loadInfo() {

        if (selectedMovie?.title == null || selectedMovie?.title == "") {
            movie_title_filme.text = selectedMovie?.name
        } else {
            movie_title_filme.text = selectedMovie?.title
        }
        movie_overview_filme.text = selectedMovie?.overview
        movie_class_filme.text = selectedMovie?.vote_average.toString()
        ratingBar.rating = (selectedMovie?.vote_average?.div(2))!!.toFloat()
        Glide.with(this@FilmeActivity).load(IMAGE_BASE + selectedMovie!!.poster)
            .into(movie_imageView_filme)
    }
}
