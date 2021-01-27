package com.pokeapp.domain.usecase.type

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.exception.MissingParamsException
import com.pokeapp.domain.model.type.Type
import com.pokeapp.domain.repository.TypeRepository
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