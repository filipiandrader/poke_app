package com.pokeapp.data.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

fun <T, R> Flow<T>.flatMap(f: (T) -> Flow<R>) = flow {
    this@flatMap.collect {
        f(it).collect { value ->
            emit(value)
        }
    }
}