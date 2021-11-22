package com.example.hamrochalachitra.data.response.movies


import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("@meta")
    val meta: Meta,
    @SerializedName("status")
    val status: String,
    @SerializedName("status_message")
    val statusMessage: String
)