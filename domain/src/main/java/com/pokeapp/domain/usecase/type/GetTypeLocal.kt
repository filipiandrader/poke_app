package com.pokeapp.domain.usecase.type

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.model.type.Type
import com.pokeapp.domain.repository.TypeRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

class GetTypeLocal(
    private val typeRepository: TypeRepository,
    scope: CoroutineScope
) : UseCase<List<Type>?, Unit>(scope) {

    override fun run(params: Unit?) = typeRepository.getAllTypeLocal()
}