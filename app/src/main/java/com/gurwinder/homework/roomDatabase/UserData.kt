package com.gurwinder.homework.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserData(
    @PrimaryKey(autoGenerate = true) var statusId: Long = 0,
    @ColumnInfo(name = "userId") var userId: String? = null,
    @ColumnInfo(name = "userName") var userName: String? = null,
    @ColumnInfo(name = "token") var token: String? = null
)