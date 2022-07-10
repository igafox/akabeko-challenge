package com.igafox.akabekochallenge.data.source.akabekolog.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "akabeko_logs")
data class AkabekoLog @JvmOverloads constructor(
    @ColumnInfo(name = "description") var description: String = "",
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "log_id") var id: Int = 0
)

