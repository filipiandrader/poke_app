package com.pokeapp.presentation

class ViewState<T>(val data: T? = null,
                   val state: State,
                   val throwable: Throwable? = null) {

    companion object {
        fun <T> success(): ViewState<T> = ViewState(state = State.SUCCESS)

        fun <T> success(t: T): ViewState<T> = ViewState(data = t, state = State.SUCCESS)

        fun <T> failure(t: Throwable) = ViewState<T>(state = State.FAILURE, throwable = t)

        fun <T> failure() = ViewState<T>(state = State.FAILURE)

        fun <T> loading() = ViewState<T>(state = State.LOADING)

//        fun <T> gettingData(t: T) = ViewState(data = t, state = State.GETTING_DATA)

        fun <T> initializing() = ViewState<T>(state = State.WAITING_DATA)
    }
}

enum class State {
    WAITING_DATA,
//    GETTING_DATA,
    LOADING,
    SUCCESS,
    FAILURE
}