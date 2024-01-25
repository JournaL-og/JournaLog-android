package com.jinin4.journalog.db.photo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// 이지윤 작성 - 24.01.22
@Dao
interface PhotoDao {

    @Query("select photo_id,memo_id,photo_url,photo_uri from photo")
    fun getAllPhotos(): List<PhotoEntity>

    @Query("select photo_id,memo_id,photo_url,photo_uri from photo where memo_id = :memo_id")
    fun getPhotoByMemoId(memo_id: Int): List<PhotoEntity>

    @Insert
    fun insertPhoto(photo: PhotoEntity): Long

    @Delete
    fun deletePhoto(photo: PhotoEntity)

    @Query("delete from photo where memo_id = :id")
    fun deletePhotoByMemoId(id: Int)

    @Update
    fun updatePhoto(photo: PhotoEntity)
}