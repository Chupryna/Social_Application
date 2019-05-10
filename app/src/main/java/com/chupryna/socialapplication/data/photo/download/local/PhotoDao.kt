package com.chupryna.socialapplication.data.photo.download.local

import androidx.room.*
import com.chupryna.socialapplication.data.model.Photo

@Dao
abstract class PhotoDao {

    @Query("SELECT * FROM photo WHERE albumId = :albumID")
    abstract fun getPhotosByAlbumID(albumID: Int): List<Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(list: List<Photo>): List<Long>

    @Delete
    abstract fun deletePhoto(photo: Photo): Int

    @Query("DELETE FROM Photo")
    abstract fun deleteAll()
}