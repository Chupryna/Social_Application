package com.chupryna.socialapplication.data.model

import com.google.gson.annotations.SerializedName

data class ImageUploadResponse(@SerializedName("success")
                               val success: Boolean = false,
                               @SerializedName("message")
                               val message: String = "",
                               @SerializedName("path")
                               val path: String? = null)