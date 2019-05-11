package com.chupryna.socialapplication.data.model

data class User(
    val fullName: String,
    val email: String,
    val password: String,
    val photoPath: String?
)