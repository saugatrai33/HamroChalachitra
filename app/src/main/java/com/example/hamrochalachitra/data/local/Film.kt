package com.example.hamrochalachitra.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Film(
    @PrimaryKey @ColumnInfo(name = "film_id") val filmId: Long,
    val title: String,
    val year: String,
    val rating: String,
    @ColumnInfo(name = "film_url") val filmUrl: String
) {

}
