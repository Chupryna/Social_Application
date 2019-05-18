package com.chupryna.socialapplication.data.model.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("username")
    val username: String = "",

    @SerializedName("email")
    val email: String = "",

    @Embedded
    @SerializedName("address")
    val address: Address,

    @SerializedName("phone")
    val phone: String = "",

    @SerializedName("website")
    val website: String = "",

    @Embedded(prefix = "company")
    @SerializedName("company")
    val company: Company
)