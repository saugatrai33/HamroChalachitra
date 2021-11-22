package com.example.hamrochalachitra.data

import com.example.hamrochalachitra.data.local.FilmDb
import com.example.hamrochalachitra.data.local.Film
import com.example.hamrochalachitra.data.remote.ApiService
import com.example.hamrochalachitra.data.response.moviedetail.MovieDetailResponse
import com.example.hamrochalachitra.data.response.movies.MovieListResponse
import com.example.hamrochalachitra.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChalachitraRepository(
    private val apiService: ApiService,
    private val db: FilmDb
) {

    suspend fun movies(limit: Int, page: Int): Result<MovieListResponse> {
        return withContext(Dispatchers.IO) {
            val response = apiService.movies(limit, page)
            Result.Success(response.body()!!)
        }
    }

    suspend fun movieDetail(movieId: Int): Result<MovieDetailResponse> {
        return withContext(Dispatchers.IO) {
            val response = apiService.movieDetail(movieId)
            Result.Success(response.body()!!)
        }
    }

    suspend fun localFilms(): Result<List<Film>> {
        return withContext(Dispatchers.IO) {
            val localMovies = db.filmDao().films()
            Result.Success(localMovies)
        }
    }

    suspend fun insertFilms(films: List<Film>) {
        withContext(Dispatchers.IO) {
            db.filmDao().insertFilms(films)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            db.filmDao().deleteAll()
        }
    }

}