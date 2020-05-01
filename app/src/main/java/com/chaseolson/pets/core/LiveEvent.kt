package com.chaseolson.pets.core

import android.os.SystemClock
import androidx.annotation.MainThread
import androidx.collection.ArraySet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.chaseolson.pets.BuildConfig

class LiveEvent : MediatorLiveData<Unit>() {

    private val observers = ArraySet<ObserverWrapper<in Unit>>()
    private var lastTimeClicked: Long = 0

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in Unit>) {
        val wrapper = ObserverWrapper(observer)
        observers.add(wrapper)
        super.observe(owner, wrapper)
    }

    @MainThread
    fun observe(owner: LifecycleOwner, block: () -> Unit) = observe(owner, Observer { it?.let { block() } })

    @MainThread
    override fun observeForever(observer: Observer<in Unit>) {
        val wrapper = ObserverWrapper(observer)
        observers.add(wrapper)
        super.observeForever(wrapper)
    }

    @MainThread
    fun observeForever(block: () -> Unit) = observeForever(Observer { it?.let { block() } })

    @MainThread
    override fun removeObserver(observer: Observer<in Unit>) {
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
    override fun setValue(t: Unit?) {
        observers.forEach { it.newValue() }
        super.setValue(t)
    }

    @MainThread
    fun throttleEvent(delayMillis: Long = 1000) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked > delayMillis || BuildConfig.DEBUG) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            value = Unit
        }
    }

    fun throttlePostEvent(delayMillis: Long = 1000) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked > delayMillis || BuildConfig.DEBUG) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            postEvent()
        }
    }

    @MainThread
    fun callEvent() {
        value = Unit
    }

    fun postEvent() {
        observers.forEach { it.newValue() }
        super.postValue(Unit)
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