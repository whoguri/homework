package com.gurwinder.homework.roomDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("select * from user")
    fun getAll():List<UserData>?

    @Insert
    fun insert(statusData: UserData)

    @Insert
    fun insertAll( vararg statusData: UserData)

    @Update
    fun update(statusData: UserData)

    @Query("DELETE from user")
    fun deleteAll()

}