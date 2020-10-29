package com.gurwinder.homework.app

import android.app.Application
import android.content.Context
import com.gurwinder.homework.di.viewmodelDi
import org.koin.android.ext.android.startKoin

class Application : Application() {
    companion object {
        var context: Context? = null
        lateinit var application: com.gurwinder.homework.app.Application
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        application = this
        startKoin(this, listOf(viewmodelDi))
    }
}