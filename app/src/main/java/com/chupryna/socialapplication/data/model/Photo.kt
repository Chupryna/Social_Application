package com.chupryna.socialapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Photo(
    @SerializedName("albumId")
    val albumId: Int = 0,

    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("url")
    val url: String = "",

    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String = "")