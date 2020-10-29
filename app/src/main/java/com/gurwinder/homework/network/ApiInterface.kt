package com.gurwinder.homework.network

import com.google.gson.JsonObject
import com.gurwinder.homework.data.User
import com.gurwinder.homework.ui.login.LoginParams
import com.gurwinder.homework.ui.login.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun doLogin(
        @Header("IMSI") IMSI: String,
        @Header("IMEI") IMEI: String,
        @Body body: LoginParams
    ): Single<LoginResponse>
}