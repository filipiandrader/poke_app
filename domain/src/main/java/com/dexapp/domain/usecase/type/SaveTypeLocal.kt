package com.dexapp.domain.usecase.type

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.exception.MissingParamsException
import com.dexapp.domain.model.type.Type
import com.dexapp.domain.repository.TypeRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 26/01/2021.
 */

class SaveTypeLocal(
    private val typeRepository: TypeRepository,
    scope: CoroutineScope
) : UseCase<Unit, SaveTypeLocal.Params>(scope) {

    override fun run(params: Params?) = when (params) {
        null -> throw MissingParamsException()
        else -> typeRepository.insertTypeLocal(params.type)
    }

    data class Params(val type: List<Type>)
}