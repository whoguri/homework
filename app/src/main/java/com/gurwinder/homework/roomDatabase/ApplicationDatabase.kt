package com.gurwinder.homework.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserData::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    companion object {
        private var sInstance: ApplicationDatabase? = null
        fun getInstance(context: Context): ApplicationDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, ApplicationDatabase::class.java, "homework.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return sInstance!!
        }
    }
}
