package com.gurwinder.homework.ui.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.gurwinder.homework.R
import com.gurwinder.homework.extension.disable
import com.gurwinder.homework.extension.enable
import com.gurwinder.homework.extension.toastL
import com.gurwinder.homework.ui.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val loginViewModel by viewModel<LoginViewModel>()
    val REQUEST_CODE=101
    val REQUEST_CODE_2=102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), REQUEST_CODE_2);
        setObserver()
        setOnClick()
    }


    private fun setObserver() {
        loginViewModel.loginObserver.observe(this, Observer {
            if(it.success){
                this.startActivity(Intent(this,HomeActivity::class.java))
                finishAffinity()
            }else{
                login.enable()
                this.toastL(it.msg)
            }
        })
    }

    private fun setOnClick() {
        login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        if (validData()) {
            val array:String
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), REQUEST_CODE);
                return;
            }
            val tm =  this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager;
            val IMSI = tm.getSubscriberId();
            val IMEI = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                tm.getTypeAllocationCode()
            } else {
                tm.getDeviceId()
            }

            loginViewModel.doLogin(username.text.toString(),password.text.toString(),IMSI,IMEI)
            login.disable()
        }
    }

    private fun validData(): Boolean {
        if (username.text.toString().isEmpty()) {
            this.toastL("Username is required")
            return     false
        } else if (password.text.toString().isEmpty()) {
            this.toastL("Password is required")
            return false
        }  else {
            return true
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    login()
                } else {
                }
            }
        }
    }
}