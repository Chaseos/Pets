package com.chaseolson.pets.oldstuff

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlinx.coroutines.*

val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)

fun View.setDebounceOnClickListener(delayMill: Long = 1000, func: () -> Unit) {
    var delayed = false
    setOnClickListener {
        if (!delayed) {
            hideInputMethodAndClearFocus()
            coroutineScope.launch {
                delayed = true
                func()
                delay(delayMill)
                delayed = false
            }
        }
    }
}

fun View.hideInputMethodAndClearFocus() {
    val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputManager?.hideSoftInputFromWindow(windowToken, 0)
    clearFocus()
}