package com.igafox.akabekochallenge.data.source.akabekolog

import com.igafox.akabekochallenge.data.Result
import com.igafox.akabekochallenge.data.source.akabekolog.model.AkabekoLog
import kotlinx.coroutines.flow.Flow

/***
 * 赤ベコログRepositoryインターフェイス
 */

interface AkabekoLogsRepository {

    fun getAkabekoLogsStream(): Flow<Result<List<AkabekoLog>>>

    suspend fun getAkabekoLogs(): Result<List<AkabekoLog>>

    fun getAkabekoLogStream(logId: Int): Flow<Result<AkabekoLog>>

    suspend fun getAkabekoLog(
        logId: Int
    ): Result<AkabekoLog>

    suspend fun saveAkabekoLog(log: AkabekoLog)

    suspend fun deleteAkabekoLog(logId: Int)

}