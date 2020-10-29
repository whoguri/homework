package com.gurwinder.homework.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import com.gurwinder.homework.R
import com.gurwinder.homework.extension.enable
import com.gurwinder.homework.extension.toastL
import com.gurwinder.homework.ui.HomeActivity
import com.gurwinder.homework.ui.login.LoginActivity
import com.gurwinder.homework.ui.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setObserver()
        viewModel.isLogin()
    }
    private fun setObserver() {
        viewModel.observer.observe(this, Observer {
            Handler(Looper.getMainLooper()).postDelayed({
                var intent= Intent(this, LoginActivity::class.java)
                if(it){
                    intent= Intent(this, HomeActivity::class.java)
                }
                startActivity(intent)
                finishAffinity()
            }, 1000)
        })
    }
}