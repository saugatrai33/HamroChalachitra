package com.example.hamrochalachitra.data.remote

import com.example.hamrochalachitra.data.response.moviedetail.MovieDetailResponse
import com.example.hamrochalachitra.data.response.movies.MovieListResponse
import com.example.hamrochalachitra.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // movies list
    @GET(Constants.MOVIES_LIST)
    suspend fun movies(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<MovieListResponse>

    @GET(Constants.MOVIE_DETAIL)
    suspend fun movieDetail(
        @Query("movie_id") movieId: Int
    ): Response<MovieDetailResponse>

}