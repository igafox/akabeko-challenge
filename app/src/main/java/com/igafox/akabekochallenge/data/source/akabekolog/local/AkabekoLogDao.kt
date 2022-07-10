package com.igafox.akabekochallenge.data.source.akabekolog.local

import androidx.room.*
import com.igafox.akabekochallenge.data.source.akabekolog.model.AkabekoLog
import kotlinx.coroutines.flow.Flow

@Dao
interface AkabekoLogDao {

    /**
     * 赤ベコログを全件監視する
     *
     * @return 赤ベコログデータ全件
     */
    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM akabeko_logs")
    fun observeAkabekoLogs(): Flow<List<AkabekoLog>>

    /**
     * 1件の赤ベコログを監視する
     *
     * @param　logId 任意の赤ベコログID
     * @return IDに紐づく1件の赤ベコログデータ
     */
    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM akabeko_logs WHERE log_id = :logId")
    fun observeAkabekoLogById(logId: Int): Flow<AkabekoLog>


    /***
     * 赤ベコログを全件取得する
     *
     * @return 赤ベコログデータ全件
     */
    @Query("SELECT * FROM akabeko_logs")
    suspend fun getAkabekoLogs(): List<AkabekoLog>


    /***
     * 1件の赤ベコログを取得する
     *
     * @param logId 任意の赤ベコログID
     * @return IDに紐づく1件の赤ベコログデータ
     */
    @Query("SELECT * FROM akabeko_logs WHERE log_id = :logId")
    suspend fun getAkabekoLogById(logId: Int): AkabekoLog?


    /**
     * 赤ベコログを追加する
     *
     * @param log　追加する赤ベコログデータ
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAkabekoLog(log: AkabekoLog)

    /**
     * 赤ベコログを更新する
     *
     * @param log 更新する赤ベコログデータ
     */
    @Update
    suspend fun updateAkabekoLog(log: AkabekoLog)

    /**
     * 赤ベコログデータを削除する
     *
     * @param logId 削除する対象の赤ベコログID
     * @return 削除された赤ベコログデータの数。成功時は必ず1が返却されます。
     */
    @Query("DELETE FROM akabeko_logs WHERE log_id = :logId")
    suspend fun deleteAkabekoLogById(logId: Int): Int

}