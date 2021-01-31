@file:Suppress("MemberVisibilityCanBePrivate")

package com.dexapp.data_remote.utils

import com.dexapp.data_remote.exception.ServerException
import org.koin.core.KoinComponent
import java.io.IOException

class RequestWrapperImpl : RequestWrapper, KoinComponent {

    @Synchronized
    override suspend fun <T> wrapperGenericResponse(call: suspend () -> GenericResponse<T>) =
            wrapper(call = call)

    @Synchronized
    override suspend fun <D> wrapper(retryCount: Int, call: suspend () -> D) : D {
        return try {
            call()
        } catch (e: Exception) {
            throw ServerException()
        }
    }
}