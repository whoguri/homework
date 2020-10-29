package com.gurwinder.homework.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import com.gurwinder.homework.R
import com.gurwinder.homework.ui.home.HomeViewModel
import com.gurwinder.homework.ui.login.LoginActivity
import com.gurwinder.homework.ui.main.MainViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val viewModel by viewModel<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setObserver()
        viewModel.getuser()
    }
    private fun setObserver() {
        viewModel.observer.observe(this, Observer {
            info.setText("User Name: "+it.userName+" | Token: "+it.token)
        })
    }
}