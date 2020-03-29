package com.pokeapp.data

sealed class Result<out T : Any> {
    class Success<out T : Any>(val data: T) : Result<T>()
    class Error(val throwable: Throwable) : Result<Throwable>()
}

class ResultRequest<out T : Any>(val data: T? = null, val throwable: Throwable? = null) {
    companion object {
        fun <T : Any> success(data: T) = ResultRequest(data = data)
        fun error(t: Throwable) = ResultRequest<Nothing>(throwable = t)
    }

}