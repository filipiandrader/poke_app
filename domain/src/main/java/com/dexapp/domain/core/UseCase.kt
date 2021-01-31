package com.dexapp.domain.core

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class UseCase<T, in Params>(private val scope: CoroutineScope): KoinComponent {
    private val contextProvider: ThreadContextProvider by inject()

    abstract fun run(params: Params? = null): Flow<T>

    operator fun invoke(
        params: Params? = null,
        onError: ((Throwable) -> Unit) = {},
        onSuccess: (T) -> Unit = {}
    ) {
        scope.launch(contextProvider.io) {
            try {
                run(params).collect {
                    withContext(contextProvider.main) {
                        onSuccess(it)
                    }
                }
            } catch (e: Exception) {
                withContext(contextProvider.main) {
                    onError(e)
                }
            }
        }

    }

    fun cancel() = scope.coroutineContext.cancelChildren()
}



