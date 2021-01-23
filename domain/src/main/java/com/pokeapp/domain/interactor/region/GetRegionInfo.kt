package com.pokeapp.domain.interactor.region

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.exception.EmptyFieldException
import com.pokeapp.domain.exception.MissingParamsException
import com.pokeapp.domain.model.region.RegionInfo
import com.pokeapp.domain.repository.RegionRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

class GetRegionInfo(
    private val repository: RegionRepository,
    scope: CoroutineScope
) : UseCase<RegionInfo, GetRegionInfo.Params>(scope) {

    override fun run(params: Params?) = when {
        params == null -> throw MissingParamsException()
        params.name.isBlank() -> throw EmptyFieldException()
        else -> repository.getRegionByName(params.name)
    }

    data class Params(var name: String)
}