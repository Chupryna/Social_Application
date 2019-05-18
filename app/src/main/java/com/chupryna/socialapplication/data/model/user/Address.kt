package com.chupryna.socialapplication.data.model.user

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Address(@SerializedName("street")
                   val street: String = "",

                   @SerializedName("suite")
                   val suite: String = "",

                   @SerializedName("city")
                   val city: String = "",

                   @ColumnInfo(name = "zip_code")
                   @SerializedName("zipcode")
                   val zipCode: String = "",

                   @Embedded
                   @SerializedName("geo")
                   val geo: Geo
)