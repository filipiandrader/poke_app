package com.pokeapp.data

class ResultRequest<out T : Any>(val data: T? = null, val throwable: Throwable? = null) {
    companion object {
        fun <T : Any> success(data: T) = ResultRequest(data = data)
        fun error(t: Throwable) = ResultRequest<Nothing>(throwable = t)
    }

}