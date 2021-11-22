package com.example.hamrochalachitra.data.response.movies


import com.google.gson.annotations.SerializedName

data class Movy(
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("background_image_original")
    val backgroundImageOriginal: String,
    @SerializedName("date_uploaded")
    val dateUploaded: String,
    @SerializedName("date_uploaded_unix")
    val dateUploadedUnix: Int,
    @SerializedName("description_full")
    val descriptionFull: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_code")
    val imdbCode: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("large_cover_image")
    val largeCoverImage: String,
    @SerializedName("medium_cover_image")
    val mediumCoverImage: String,
    @SerializedName("mpa_rating")
    val mpaRating: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("small_cover_image")
    val smallCoverImage: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_english")
    val titleEnglish: String,
    @SerializedName("title_long")
    val titleLong: String,
    @SerializedName("torrents")
    val torrents: List<Torrent>,
    @SerializedName("url")
    val url: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("yt_trailer_code")
    val ytTrailerCode: String
)