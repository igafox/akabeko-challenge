package com.igafox.akabekochallenge.data.source.akabekolog.local

import com.igafox.akabekochallenge.data.Result
import com.igafox.akabekochallenge.data.Result.Success
import com.igafox.akabekochallenge.data.Result.Error
import com.igafox.akabekochallenge.data.source.akabekolog.AkabekoLogDataSource
import com.igafox.akabekochallenge.data.source.akabekolog.model.AkabekoLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlin.Exception

class AkabekoLogLocalDataSource internal constructor(
    private val akabekoLogDao: AkabekoLogDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : AkabekoLogDataSource {

    override fun getAkabekoLogsStream(): Flow<Result<List<AkabekoLog>>> {
        return akabekoLogDao.observeAkabekoLogs().map {
            Success(it)
        }
    }

    override fun getAkabekoLogStream(akabekoLogId: Int): Flow<Result<AkabekoLog>> {
        return akabekoLogDao.observeAkabekoLogById(akabekoLogId).map {
            Success(it)
        }
    }

    override suspend fun getAkabekoLogs(): Result<List<AkabekoLog>> = withContext(ioDispatcher) {
        return@withContext try {
            Success(akabekoLogDao.getAkabekoLogs())
        } catch (e: Exception) {
            Error(e)
        }
    }

    override suspend fun getAkabekoLog(akabekoLogId: Int): Result<AkabekoLog> = withContext(ioDispatcher) {
        return@withContext try {
            val task = akabekoLogDao.getAkabekoLogById(akabekoLogId)
            if (task != null) {
                return@withContext Success(task)
            } else {
                return@withContext Error(Exception("AkabekoLog not found."))
            }
        } catch (e:Exception) {
            return@withContext Error(e)
        }
    }

    override suspend fun saveAkabekoLog(akabekoLog: AkabekoLog) = withContext(ioDispatcher){
        akabekoLogDao.insertAkabekoLog(akabekoLog)
    }

    override suspend fun deleteAkabekoLog(akabekoLogId: Int) = withContext<Unit>(ioDispatcher) {
        akabekoLogDao.deleteAkabekoLogById(akabekoLogId)
    }

}