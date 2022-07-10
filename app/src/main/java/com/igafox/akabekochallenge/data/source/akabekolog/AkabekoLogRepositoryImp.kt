package com.igafox.akabekochallenge.data.source.akabekolog

import com.igafox.akabekochallenge.data.Result
import com.igafox.akabekochallenge.data.source.akabekolog.model.AkabekoLog
import kotlinx.coroutines.flow.Flow

class AkabekoLogRepositoryImp(
    private val localDataSource: AkabekoLogDataSource
) : AkabekoLogsRepository {
    
    override fun getAkabekoLogsStream(): Flow<Result<List<AkabekoLog>>> {
        return localDataSource.getAkabekoLogsStream()
    }

    override suspend fun getAkabekoLogs(): Result<List<AkabekoLog>> {
        return localDataSource.getAkabekoLogs()
    }

    override fun getAkabekoLogStream(logId: Int): Flow<Result<AkabekoLog>> {
        return localDataSource.getAkabekoLogStream(logId)
    }
    
    override suspend fun getAkabekoLog(
        logId: Int
    ): Result<AkabekoLog> {
        return localDataSource.getAkabekoLog(logId)
    }

    override suspend fun saveAkabekoLog(log: AkabekoLog) {
        return localDataSource.saveAkabekoLog(log)
    }

    override suspend fun deleteAkabekoLog(logId: Int) {
        return localDataSource.deleteAkabekoLog(logId)
    }

}