package com.hedipoduarte.nttfilmes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hedipoduarte.nttfilmes.models.Movie

class MovieAdapter(
    private val movies : List<Movie>,
    val context: Context,
    var clickMovie: ClickMovie
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)

        val holder = MovieViewHolder(view)

        return holder
    }

    interface ClickMovie{

        fun clickMovie(movies: Movie)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        val item = movies.get(position)

        if(item.title == null || item.title == "") {
            holder.movie_title.text = item.name
        }else{
            holder.movie_title.text = item.title
        }
        if(item.release == null || item.release == "") {
            holder.movie_release_date.text = item.first_air_date
        }else{
            holder.movie_release_date.text = item.release
        }

        Glide.with(context).load(IMAGE_BASE+item.poster).into(holder.movie_poster)



        holder.cardMovie.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

                clickMovie.clickMovie(item)

            }

        })

    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val movie_title = itemView.findViewById<TextView>(R.id.movie_title)
        val movie_release_date = itemView.findViewById<TextView>(R.id.movie_release_date)
        val movie_poster = itemView.findViewById<ImageView>(R.id.movie_poster)
        val cardMovie = itemView.findViewById<ConstraintLayout>(R.id.cardMovie)

    }

}