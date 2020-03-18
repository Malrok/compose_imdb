package com.mrk.composeimdb.ui.common

import androidx.compose.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * cf https://medium.com/swlh/android-mvi-with-jetpack-compose-b0890f5156ac
 */
@Composable
fun <T> observe(data: LiveData<T>): T? {
    var result by state { data.value }
    val observer = remember { Observer<T> { result = it } }

    onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    return result
}

//@Composable
//fun <T> observe(observable: () -> LiveData<T>, result: (T) -> Unit) {
//    val thing = stateFor<T?> { null }
//    onActive {
//        val observer =
//            Observer<T> {
//                thing.value = it
//                result(it)
//            }
//        with(observable()) {
//            observeForever(observer)
//            onDispose {
//                removeObserver(observer)
//            }
//        }
//
//    }
//    thing.value
//}