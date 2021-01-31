package com.dexapp.domain.usecase.region

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.exception.EmptyFieldException
import com.dexapp.domain.exception.MissingParamsException
import com.dexapp.domain.model.region.RegionInfo
import com.dexapp.domain.repository.RegionRepository
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