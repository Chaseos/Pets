package com.chaseolson.pets.ui.utils

import android.os.SystemClock
import com.chaseolson.pets.BuildConfig

private var lastTimeClicked: Long = 0

fun simpleThrottle(delayMillis: Long = 1000, block: () -> Unit) {
    if (SystemClock.elapsedRealtime() - lastTimeClicked > delayMillis || BuildConfig.DEBUG) {
        lastTimeClicked = SystemClock.elapsedRealtime()
        block()
    }
}