package com.dexapp.data_remote.utils

interface RequestWrapper {

    suspend fun <T> wrapperGenericResponse(
            call: suspend () -> GenericResponse<T>
    ): GenericResponse<T>

    suspend fun <D> wrapper(
            retryCount: Int = 0,
            call: suspend () -> D
    ): D
}