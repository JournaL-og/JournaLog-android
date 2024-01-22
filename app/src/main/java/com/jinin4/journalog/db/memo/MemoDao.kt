package com.jinin4.journalog.db.memo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.Update
import com.jinin4.journalog.Converter
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.sql.Timestamp

// 이상원 - 24.01.19
@Dao
interface MemoDao {
    @Query("select memo_id,content,timestamp,color_id from memo")
    fun getAllMemo(): List<MemoEntity>

    @Query("select memo_id,content,timestamp,color_id from memo where color_id = :color_id")
    fun getMemoColorId(color_id: Int): List<MemoEntity>

    @Query("select memo_id,content,timestamp,color_id from memo where memo_id = :id")
    fun getMemoById(id: Int): MemoEntity

//    @Query("select memo_id,content,date(timestamp),color_id from memo where timestamp = :timestamp")
//    fun getMemoByTimestamp(timestamp: String): List<MemoEntity>

    @Query("SELECT memo_id, content, strftime('%H:%M', timestamp) as timestamp, color_id FROM memo WHERE date(timestamp) = :timestamp")
    fun getMemoByTimestamp(timestamp: String): List<MemoEntity>

    @Query("SELECT DISTINCT date(timestamp) AS timestamp FROM memo")
    fun getDistinctCalendarDays(): List<String>
//    select date('now') as date,
//    time('now') as time,
//    datetime('now') as datetime;
//      date        time        datetime
//      ----------  ----------  -------------------
//      2019-11-06  15:03:33    2019-11-06 15:03:33

    @Insert
    fun insertMemo(memo: MemoEntity): Long

    @Delete
    fun deleteMemo(memo: MemoEntity)

    // 최성혁 - 24.01.22
    @Query("DELETE FROM memo")
    fun deleteAllMemos()

    @Update
    fun updateMemo(memo: MemoEntity)
}