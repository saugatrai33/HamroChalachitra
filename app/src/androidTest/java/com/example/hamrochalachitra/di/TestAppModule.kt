package com.example.hamrochalachitra.di

import android.content.Context
import androidx.room.Room
import com.example.hamrochalachitra.data.local.FilmDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import javax.inject.Named

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(
        @ApplicationContext context: Context,
        testCoroutineDispatcher: TestCoroutineDispatcher
    ): FilmDb =
        Room.inMemoryDatabaseBuilder(
            context,
            FilmDb::class.java
        )
            .setTransactionExecutor(testCoroutineDispatcher.asExecutor())
            .setQueryExecutor(testCoroutineDispatcher.asExecutor())
            .allowMainThreadQueries()
            .build()


    @Provides
    fun provideTestCoroutineDispatcher() = TestCoroutineDispatcher()

    @Provides
    fun provideTestCoroutineScope(testCoroutineDispatcher: TestCoroutineDispatcher) =
        TestCoroutineScope(testCoroutineDispatcher)

}