package com.chupryna.socialapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ToDo(
    @SerializedName("userId")
    val userId: Int = 0,

    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("completed")
    val completed: Boolean = false)