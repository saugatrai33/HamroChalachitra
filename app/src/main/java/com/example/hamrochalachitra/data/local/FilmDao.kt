package com.example.hamrochalachitra.data.local

import androidx.room.*

@Dao
interface FilmDao {

    @Query("SELECT * FROM film ORDER BY film_id DESC")
    suspend fun films(): List<Film>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<Film>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetFilm(film: Film)

    @Query("DELETE FROM film")
    suspend fun deleteAll()

}