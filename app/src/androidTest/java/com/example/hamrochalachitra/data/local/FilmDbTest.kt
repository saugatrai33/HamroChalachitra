package com.example.hamrochalachitra.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class FilmDbTest {

    @Inject
    @Named("test_db")
    lateinit var db: FilmDb

    private lateinit var dao: FilmDao

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        hiltRule.inject()
        dao = db.filmDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun `insert_film_contains_after_fetching`() = runBlockingTest {
        val film = Film(
            123456, "Avengers", "2015", "8.9",
            "https://avengers.com/img.jpg"

        )
        dao.insetFilm(film)
        val allFilms = dao.films()
        assertThat(allFilms.contains(film)).isTrue()
    }

}