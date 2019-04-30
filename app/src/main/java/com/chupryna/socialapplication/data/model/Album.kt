package com.chupryna.socialapplication.data.model

import com.google.gson.annotations.SerializedName

data class Album(@SerializedName("userId")
                 val userId: Int = 0,
                 @SerializedName("id")
                 val id: Int = 0,
                 @SerializedName("title")
                 val title: String = "")