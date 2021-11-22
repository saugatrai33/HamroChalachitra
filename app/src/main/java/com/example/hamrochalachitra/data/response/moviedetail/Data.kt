package com.example.hamrochalachitra.data.response.moviedetail


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("movie")
    val movie: Movie
)