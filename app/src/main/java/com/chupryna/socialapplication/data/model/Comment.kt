package com.chupryna.socialapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Comment(
    @SerializedName("postId")
    val postId: Int = 0,

    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("email")
    val email: String = "",

    @SerializedName("body")
    val body: String = "")