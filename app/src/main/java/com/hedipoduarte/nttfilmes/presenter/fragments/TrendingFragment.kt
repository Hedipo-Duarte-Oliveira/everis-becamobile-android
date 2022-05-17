package com.hedipoduarte.nttfilmes.presenter.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hedipoduarte.nttfilmes.R
import com.hedipoduarte.nttfilmes.data.api.MovieApiInterface
import com.hedipoduarte.nttfilmes.data.model.MovieResponse
import com.hedipoduarte.nttfilmes.domain.MovieApiService
import com.hedipoduarte.nttfilmes.domain.model.Movie
import com.hedipoduarte.nttfilmes.presenter.FilmeActivity
import com.hedipoduarte.nttfilmes.presenter.adapter.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingFragment :
    Fragment(),
    MovieAdapter.ClickMovie {

    private lateinit var rv_movies_list_trending: RecyclerView
    var viewLayout: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewLayout = inflater.inflate(R.layout.fragment_trending, container, false)

        rv_movies_list_trending = viewLayout!!.findViewById(R.id.rv_movies_list_trending)

        rv_movies_list_trending.layoutManager = LinearLayoutManager(activity)
        rv_movies_list_trending.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            rv_movies_list_trending.adapter = MovieAdapter(movies, requireContext(), this)
        }

        return viewLayout
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieListTrending().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
        })
    }

    override fun clickMovie(movies: Movie) {
        val intent = Intent(requireContext(), FilmeActivity::class.java)
        intent.putExtra("movies", movies)
        startActivity(intent)
    }
}
