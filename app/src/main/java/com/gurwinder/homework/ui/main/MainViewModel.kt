package com.gurwinder.homework.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gurwinder.homework.di.BaseViewModel
import com.gurwinder.homework.di.CommonState
import com.gurwinder.homework.roomDatabase.DatabaseRepo
import com.gurwinder.homework.data.User
import com.gurwinder.homework.di.Transformers
import com.gurwinder.homework.network.Api
import com.gurwinder.homework.network.ResponseConsumer
import com.gurwinder.homework.network.apiHitter
import com.gurwinder.homework.roomDatabase.UserData

class MainViewModel(private val databaseRepo: DatabaseRepo) : BaseViewModel() {
    val liveData = MutableLiveData<Boolean>()
    val observer get() = liveData
    fun isLogin() {
        var users=  databaseRepo.getUsers()
        if( users===null || users.size==0){
            liveData.value = false
        }else {
            liveData.value = true
        }
    }
}