package com.pokeapp.data_remote.utils

interface RequestWrapper {

    suspend fun <T> wrapperGenericResponse(
            call: suspend () -> T
    ): T

    suspend fun <D> wrapper(
            retryCount: Int = 0,
            call: suspend () -> D
    ): D
}