package com.chaseolson.pets.core

import androidx.annotation.MainThread
import com.hadilq.liveevent.LiveEvent

@MainThread
fun LiveEvent<Unit>.call() {
    value = null
}