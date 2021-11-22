package com.example.hamrochalachitra.ui

import android.app.Application
import androidx.lifecycle.*
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.hamrochalachitra.data.ChalachitraRepository
import com.example.hamrochalachitra.data.local.Film
import com.example.hamrochalachitra.data.response.moviedetail.Movie
import com.example.hamrochalachitra.data.response.movies.MovieListResponse
import com.example.hamrochalachitra.data.response.movies.Movy
import com.example.hamrochalachitra.utils.Constants.TAG_OUTPUT
import com.example.hamrochalachitra.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ChalachitraRepository
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movy>>()
    val movies: LiveData<List<Movy>> = _movies

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getMovies(limit: Int, page: Int) {

        viewModelScope.launch {

            val result = try {
                repository.movies(limit, page)
            } catch (e: Exception) {
                Result.Error(e)
            }

            when (result) {
                is Result.Success<MovieListResponse> -> {
                    val remoteMovies = result.data.data.movies
                    val localFilms = mutableListOf<Film>()

                    remoteMovies.map { movy ->
                        val uId = movy.id.toLong()
                        val title = movy.title
                        val rating = movy.rating.toString()
                        val year = movy.year.toString()
                        val filmUrl = movy.backgroundImage
                        val film = Film(uId, title, year, rating, filmUrl)
                        localFilms.add(film)
                    }

                    try {
                        repository.insertFilms(localFilms)
                    } catch (e: Exception) {
                        _error.postValue("Error inserting films.")
                    }
                    _movies.postValue(remoteMovies)
                }
                else -> {
                    _error.postValue("Failed to fetch movies.")
                }
            }
        }
    }

    fun getMovieDetail(movieId: Int) {

        viewModelScope.launch {
            val result = try {
                repository.movieDetail(movieId)
            } catch (e: Exception) {
                Result.Error(e)
            }

            when (result) {
                is Result.Success -> {
                    _movie.postValue(result.data.data.movie)
                }
                else -> {
                    _error.postValue("Something went wrong.")
                }
            }
        }

    }

}