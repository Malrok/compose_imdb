package com.mrk.composeimdb.ui.common

import androidx.compose.Composable
import androidx.compose.onActive
import androidx.compose.stateFor
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * cf https://medium.com/swlh/android-mvi-with-jetpack-compose-b0890f5156ac
 */
//@Composable
//fun <T> observe(data: LiveData<T>) {
//    val result = state { data.value }
//    val observer = remember { Observer<T> { result.value = it } }
//
//    onCommit(data) {
//        data.observeForever(observer)
//        onDispose { data.removeObserver(observer) }
//    }
//
//    result.value
//}

@Composable
fun <T> observe(observable: () -> LiveData<T>, result: (T) -> Unit) {
    val thing = stateFor<T?> { null }
    onActive {
        val observer =
            Observer<T> {
                thing.value = it
                result(it)
            }
        with(observable()) {
            observeForever(observer)
            onDispose {
                removeObserver(observer)
            }
        }

    }
    thing.value
}