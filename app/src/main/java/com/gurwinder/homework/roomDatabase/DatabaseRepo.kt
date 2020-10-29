package com.gurwinder.homework.roomDatabase

import com.gurwinder.homework.app.Application
class DatabaseRepo {
    private val userDao by lazy { ApplicationDatabase.getInstance(Application.context!!).getUserDao() }
    fun insertUser(userData: UserData) {
        userDao.insert(userData)
    }
    fun getUsers():ArrayList<UserData>?{
        return userDao.getAll() as ArrayList<UserData>
    }

    fun updateUser(userData: UserData) {
        userDao.update(userData)
    }

    fun clearAllDatabase() {
     userDao.deleteAll()
    }
}