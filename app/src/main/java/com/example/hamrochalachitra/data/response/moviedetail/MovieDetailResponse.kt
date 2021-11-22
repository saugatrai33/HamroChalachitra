package com.example.hamrochalachitra.data.response.moviedetail


import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("@meta")
    val meta: Meta,
    @SerializedName("status")
    val status: String,
    @SerializedName("status_message")
    val statusMessage: String
)