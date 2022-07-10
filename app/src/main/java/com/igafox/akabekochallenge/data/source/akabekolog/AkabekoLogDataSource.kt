package com.igafox.akabekochallenge.data.source.akabekolog

import com.igafox.akabekochallenge.data.Result
import com.igafox.akabekochallenge.data.source.akabekolog.model.AkabekoLog
import kotlinx.coroutines.flow.Flow

interface AkabekoLogDataSource {

    fun getAkabekoLogsStream(): Flow<Result<List<AkabekoLog>>>

    suspend fun getAkabekoLogs(): Result<List<AkabekoLog>>

    fun getAkabekoLogStream(akabekoLogId: Int): Flow<Result<AkabekoLog>>

    suspend fun getAkabekoLog(akabekoLogId: Int): Result<AkabekoLog>
    
    suspend fun saveAkabekoLog(akabekoLog:AkabekoLog)

    suspend fun deleteAkabekoLog(akabekoLogId: Int)
    
}