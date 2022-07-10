package com.igafox.akabekochallenge.data.source.akabekolog.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.igafox.akabekochallenge.MainCoroutineRule
import com.igafox.akabekochallenge.data.database.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class AkabekoLogLocalDataSourceTest {

    private lateinit var localDataString: AkabekoLogLocalDataSource
    private lateinit var database: AppDatabase

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun cleanUp() {
        database.close()
    }



}