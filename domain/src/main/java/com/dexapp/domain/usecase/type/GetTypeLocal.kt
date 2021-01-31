package com.dexapp.domain.usecase.type

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.model.type.Type
import com.dexapp.domain.repository.TypeRepository
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