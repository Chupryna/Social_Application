package com.chupryna.socialapplication.data.model

import android.net.Uri

data class User(
    val fullName: String,
    val email: String,
    val password: String,
    val photoUri: Uri?
)