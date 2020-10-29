package com.gurwinder.homework.network

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


class ResponseConsumer<T>(val onRequestSuccess: (T) -> Unit, val onRequestError: (String) -> Unit) : SingleObserver<T> {
    override fun onSuccess(t: T) {
        onRequestSuccess(t)
    }

    override fun onSubscribe(d: Disposable) {}

    override fun onError(e: Throwable) {
        if (e is HttpException) {
            val responseBody = e.response().errorBody()
            onRequestError(getErrorMessage(responseBody!!))
        } else if (e is SocketTimeoutException) {
            onRequestError("Request Timeout")
        } else if (e is IOException) {
            onRequestError(e?.localizedMessage ?: "Network Error")
        } else {
            onRequestError(e.localizedMessage)
        }
    }

    private fun getErrorMessage(responseBody: ResponseBody): String {
        try {
            val jsonObject = JSONObject(responseBody.string())
            val errorMessage = jsonObject.optString("Message")
            if(errorMessage!=null)
            return errorMessage
            else{
                val errorArrayList=jsonObject.optJSONArray("errors")
                if(errorArrayList!=null&& errorArrayList.length()>0){
                    val error_data =errorArrayList.getJSONObject(0)
                    return error_data.optString("message")
                }else return ""
            }

        } catch (e: Exception) {
            return e.message!!
        }
    }
}