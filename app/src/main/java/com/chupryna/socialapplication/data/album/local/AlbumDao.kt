package com.chupryna.socialapplication.data.album.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.chupryna.socialapplication.data.model.Album

@Dao
abstract class AlbumDao {

    @Query("SELECT * FROM Album")
    abstract fun getAlbums(): List<Album>

    @Query("SELECT * FROM Album WHERE id = :id")
    abstract fun getAlbumById(id: Int): Album

    @Insert
    abstract fun insert(albums: List<Album>): List<Long>

    @Delete
    abstract fun deleteAlbum(album: Album): Int

    @Query("DELETE FROM Album")
    abstract fun deleteAll()
}