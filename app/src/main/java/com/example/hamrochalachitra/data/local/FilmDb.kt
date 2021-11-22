package com.example.hamrochalachitra.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Film::class],
    version = 2,
    exportSchema = false
)
abstract class FilmDb : RoomDatabase(){
    abstract fun filmDao(): FilmDao
}