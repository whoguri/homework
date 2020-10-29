package com.gurwinder.homework.di

import com.gurwinder.homework.roomDatabase.DatabaseRepo
import com.gurwinder.homework.ui.login.LoginRepo
import com.gurwinder.homework.ui.login.LoginViewModel
import com.gurwinder.homework.ui.home.HomeViewModel
import com.gurwinder.homework.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewmodelDi = module {
    single { DatabaseRepo() }
    single { LoginRepo() }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { MainViewModel(get()) }

}