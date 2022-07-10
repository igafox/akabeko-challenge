package com.igafox.akabekochallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.igafox.akabekochallenge.data.source.akabekolog.local.AkabekoLogDao
import com.igafox.akabekochallenge.data.source.akabekolog.model.AkabekoLog

@Database(entities = [AkabekoLog::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun akabekoLogDao(): AkabekoLogDao

}