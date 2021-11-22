package com.example.hamrochalachitra.di

import android.content.Context
import androidx.room.Room
import com.example.hamrochalachitra.data.local.FilmDb
import com.example.hamrochalachitra.data.local.FilmDao
import com.example.hamrochalachitra.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): FilmDb =
        Room.databaseBuilder(
            context,
            FilmDb::class.java,
            Constants.DB_NAME
        ).build()

    @Singleton
    @Provides
    fun provideFilDao(filmDb: FilmDb): FilmDao =
        filmDb.filmDao()

}