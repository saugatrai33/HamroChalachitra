package com.example.hamrochalachitra.di

import com.example.hamrochalachitra.data.ChalachitraRepository
import com.example.hamrochalachitra.data.local.FilmDb
import com.example.hamrochalachitra.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideChalachitraRepository(
        apiService: ApiService,
        db: FilmDb
    ): ChalachitraRepository = ChalachitraRepository(apiService, db)
}