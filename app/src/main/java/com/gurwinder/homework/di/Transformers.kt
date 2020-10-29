package com.gurwinder.homework.di

import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

object Transformers {
    fun <T> applyCtoMonS(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(40, TimeUnit.SECONDS)
        }
    }
}