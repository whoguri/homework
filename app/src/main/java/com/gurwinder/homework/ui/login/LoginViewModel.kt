package com.gurwinder.homework.ui.login

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

class LoginViewModel(private val databaseRepo: DatabaseRepo,private val loginRepo: LoginRepo) : BaseViewModel() {
    val loginLiveData = MutableLiveData<CommonState<User>>()
    val loginObserver get() = loginLiveData
    fun doLogin(username:String, password:String,IMSI:String?,IMEI:String?) {
        apiHitter().doLogin(IMSI!!,IMEI!!,
            LoginParams(username,password)
        ).compose(Transformers.applyCtoMonS()).subscribe(ResponseConsumer({
            if( it.user===null){
                loginLiveData.value = CommonState(data = null,success = false, msg = it.errorMessage)
            }else {
                val user=it.user!!
                loginLiveData.value = CommonState(data = it.user, success = true)
                databaseRepo.insertUser(UserData(userId = user.userId,userName = user.userName,token = Api.token ))
                Api.token=""
            }
        }, {
            loginLiveData.value = CommonState(data = null,success = false, msg = it)
        }))
    }
}

