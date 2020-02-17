package com.mrk.composeimdb.ui.common

import androidx.compose.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * cf https://medium.com/swlh/android-mvi-with-jetpack-compose-b0890f5156ac
 */
fun <T> observe(data: LiveData<T>) = effectOf<T?> {
    val result = +state<T?> { data.value }
    val observer = +memo { Observer<T> { result.value = it } }

    +onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    result.value
}