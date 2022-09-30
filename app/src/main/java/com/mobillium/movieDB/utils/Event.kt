package com.mobillium.movieDB.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class Event<out T>(private val content: T, private val singleCall: Boolean = false) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? = if (singleCall && hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }
    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, func: (T) -> Unit) {
    observe(owner, EventObserver { func.invoke(it) })
}
