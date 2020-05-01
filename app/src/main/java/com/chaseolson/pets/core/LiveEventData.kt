package com.chaseolson.pets.core

import android.os.SystemClock
import androidx.annotation.MainThread
import androidx.collection.ArraySet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.chaseolson.pets.BuildConfig

class LiveEventData<T>() : MediatorLiveData<T>() {
    constructor(value: T) : this() { super.setValue(value) }

    private val observers = ArraySet<ObserverWrapper<in T>>()
    private var oldValue: T? = null
    private var lastTimeClicked: Long = 0

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val wrapper = ObserverWrapper(observer)
        observers.add(wrapper)
        super.observe(owner, wrapper)
    }

    @MainThread
    fun observe(owner: LifecycleOwner, block: (T) -> Unit) = observe(owner, Observer { it?.let { block(it) } })

    @MainThread
    override fun observeForever(observer: Observer<in T>) {
        val wrapper = ObserverWrapper(observer)
        observers.add(wrapper)
        super.observeForever(wrapper)
    }

    @MainThread
    fun observeForever(block: (T) -> Unit) = observeForever(Observer { it?.let { block(it) } })

    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        if (observers.remove(observer)) {
            super.removeObserver(observer)
            return
        }
        val iterator = observers.iterator()
        while (iterator.hasNext()) {
            val wrapper = iterator.next()
            if (wrapper.observer == observer) {
                iterator.remove()
                super.removeObserver(wrapper)
                break
            }
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        observers.forEach { it.newValue() }
        super.setValue(t)
    }

    fun postEvent(t: T?) {
        observers.forEach { it.newValue() }
        super.postValue(t)
    }

    @MainThread
    fun throttleEvent(newValue: T?, delayMillis: Long = 1000) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked > delayMillis || newValue != oldValue || newValue == null || BuildConfig.DEBUG) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            oldValue = newValue
            value = newValue
        }
    }

    fun throttlePostEvent(newValue: T?, delayMillis: Long = 1000) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked > delayMillis || newValue != oldValue || newValue == null || BuildConfig.DEBUG) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            oldValue = newValue
            postEvent(newValue)
        }
    }

    private class ObserverWrapper<T>(val observer: Observer<T>) : Observer<T> {

        private var pending = false

        override fun onChanged(t: T?) {
            if (pending) {
                pending = false
                observer.onChanged(t)
            }
        }

        fun newValue() {
            pending = true
        }
    }
}
