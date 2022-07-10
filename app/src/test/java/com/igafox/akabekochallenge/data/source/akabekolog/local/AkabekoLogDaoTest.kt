package com.igafox.akabekochallenge.data.source.akabekolog.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.igafox.akabekochallenge.MainCoroutineRule
import com.igafox.akabekochallenge.data.database.AppDatabase
import com.igafox.akabekochallenge.data.source.akabekolog.model.AkabekoLog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AkabekoLogDaoTest {

    private lateinit var database: AppDatabase

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDatabase() {
        //インメモリデータベースを使用するため、プロセス終了時にデータが削除される
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertAkabekoLogAndGetId() = runTest {
        //データ追加
        val log = AkabekoLog("これは赤ベコ",1)
        database.akabekoLogDao().insertAkabekoLog(log)

        //データ取得
        val loadedLog = database.akabekoLogDao().getAkabekoLogById(log.id)

        //取得したデータが正しいか検証
        assertThat(loadedLog as AkabekoLog, notNullValue())
        assertThat(loadedLog.id, `is`(log.id))
        assertThat(loadedLog.description, `is`(log.description))
    }

    @Test
    fun inserAkabekoLogReplaceOnConflict() = runTest {
        //データ追加
        val log = AkabekoLog("これは赤ベコ",1)
        database.akabekoLogDao().insertAkabekoLog(log)

        //同じIDでデータ追加
        val newLog = AkabekoLog("これは赤ベコのはず", id = log.id)
        database.akabekoLogDao().insertAkabekoLog(newLog)

        //同じIDのデータが上書きされてるか検証
        val loadedLog = database.akabekoLogDao().getAkabekoLogById(log.id)
        assertThat(loadedLog?.id, `is`(log.id))
        assertThat(loadedLog?.description, `is`("これは赤ベコのはず"))
    }

    @Test
    fun insertAkabekoLogGetTasks() = runTest {
        //データ追加
        val log = AkabekoLog("これは赤ベコ",1)
        database.akabekoLogDao().insertAkabekoLog(log)

        val logs = database.akabekoLogDao().getAkabekoLogs()

        assertThat(logs.size, `is`(1))
        assertThat(logs.first().id, `is`(log.id))
        assertThat(logs.first().description, `is`(log.description))
    }

    @Test
    fun updateAkabekoLogAndGetById() = runTest {
        //データ追加
        val originalLog = AkabekoLog("これは赤ベコ",1)
        database.akabekoLogDao().insertAkabekoLog(originalLog)

        //同じIDでデータ上書き
        val updatedLog = AkabekoLog("これは赤ベコのはず",originalLog.id)
        database.akabekoLogDao().updateAkabekoLog(updatedLog)

        //同じIDのデータが上書きされてるか検証
        val loadedLog = database.akabekoLogDao().getAkabekoLogById(originalLog.id)
        assertThat(loadedLog?.id, `is`(originalLog.id))
        assertThat(loadedLog?.description, `is`("これは赤ベコのはず"))
    }

    @Test
    fun deleteAkabekoLogsByIdAndGetTasks() = runTest {
        //データ追加
        val log = AkabekoLog("これは赤ベコ",1)
        database.akabekoLogDao().insertAkabekoLog(log)

        //データ削除
        database.akabekoLogDao().deleteAkabekoLogById(log.id)

        //空の結果が返ってくるか検証
        val logs = database.akabekoLogDao().getAkabekoLogs()
        assertThat(logs.isEmpty(), `is`(true))
    }

}