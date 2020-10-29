package com.gurwinder.homework.ui.login

import com.gurwinder.homework.data.User

data class LoginResponse(
    var errorCode: String,
    var errorMessage: String,
    var user:User?=null
)