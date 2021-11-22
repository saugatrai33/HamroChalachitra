package com.example.hamrochalachitra.ui.film

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hamrochalachitra.data.response.movies.Movy
import com.example.hamrochalachitra.databinding.ItemMovyBinding
import javax.inject.Inject

class FilmAdapter @Inject constructor() : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    private val movies: ArrayList<Movy> = ArrayList()

    private lateinit var movieItemClickListener: OnMovieItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            ItemMovyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(movy = movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class FilmViewHolder(private val binding: ItemMovyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movy: Movy) {
            val uri: Uri = Uri.parse(movy.backgroundImage)
            binding.icMovieBg.setImageURI(uri)
            binding.title.text = movy.title
            binding.year.text = movy.year.toString()
            binding.rating.text = movy.rating.toString()
            binding.movieItem.setOnClickListener {
                movieItemClickListener.onMovieItemClick(movy.id)
            }
        }

    }

    fun addMovies(movieList: List<Movy>) {
        this.movies.apply {
            clear()
            addAll(movieList)
        }
    }

    fun setClickListener(onMovieItemClickListener: OnMovieItemClickListener) {
        this.movieItemClickListener = onMovieItemClickListener
    }

    interface OnMovieItemClickListener {
        fun onMovieItemClick(movieId: Int)
    }

}