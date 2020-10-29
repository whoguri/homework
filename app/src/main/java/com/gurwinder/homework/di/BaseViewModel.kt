package com.gurwinder.homework.di

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.standalone.KoinComponent


open class BaseViewModel : ViewModel(), KoinComponent {
    val disposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}