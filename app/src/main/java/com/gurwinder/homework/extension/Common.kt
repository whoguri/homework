package com.gurwinder.homework.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.gurwinder.homework.R
import com.gurwinder.homework.app.Application
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

inline fun <reified T> Context.openA(extras: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.extras()
    startActivity(intent)
}

fun <E> List<E>.toArrayList(): ArrayList<E> {

    val newList = arrayListOf<E>()
    newList.addAll(this)
    return newList
}

fun String.deCapitalize():String{
    this.replace(" ","")
    return this.substring(0,1).toLowerCase()+ this.substring(1)
}

fun Button.disable(){
    this.isEnabled= false
}
fun Button.enable(){
    this.isEnabled= true
}

fun Int.convertDpToPx(context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources
            .displayMetrics
    ).toInt()
}

fun toast(msg: String) {
    val toast = Toast.makeText(Application.context, msg, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.TOP,0, 80)
    toast.show()
}

fun Context.toastL(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}


fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}



///////////////////////////////////////// VIEW ////////////////////////////////////

inline fun EditText.observeTextChange(crossinline body: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            body(p0.toString())
        }
    })
}

fun View.animateX(value: Float) {
    with(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, value)) {
        duration = 3500
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
        interpolator = LinearInterpolator()
        start()
    }
}

fun View.animateY(value: Float) {
    with(ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, value)) {
        duration = 3500
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
        interpolator = LinearInterpolator()
        start()
    }
}


fun Int.inflate(viewGroup: ViewGroup): View {
    return LayoutInflater.from(viewGroup.context).inflate(this, viewGroup, false)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.toggleVisibility() {
    when (this.visibility) {
        View.VISIBLE -> this.gone()
        View.INVISIBLE -> this.visible()
        View.GONE -> this.visible()
    }
}

fun View.alphaGone() {
    animate()
        .alpha(0f)
        .setDuration(500)
        .setInterpolator(AccelerateDecelerateInterpolator())
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                this@alphaGone.gone()
            }
        })
}

fun View.alphaVisible() {
    visible()
    animate()
        .alpha(0f)
        .setDuration(500)
        .interpolator = AccelerateDecelerateInterpolator()
}



///////////////////////////////////////// COMMON ////////////////////////////////////

inline fun <T> T.executeSafe(body: () -> Unit) {
    try {
        body.invoke()
    } catch (e: Exception) {

    }
}

fun <T> T.isNull(): Boolean {
    return this == null
}

fun <T> T.isNotNull(): Boolean {
    return this != null
}

inline infix operator fun Int.times(action: (Int) -> Unit) {
    var i = 0
    while (i < this) {
        action(i)
        i++
    }
}
