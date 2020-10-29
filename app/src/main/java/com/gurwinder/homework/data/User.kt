package com.gurwinder.homework.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
data class User(
     var userId: String? = "",
     var userName: String? = "",
     var token: String? = ""
)

