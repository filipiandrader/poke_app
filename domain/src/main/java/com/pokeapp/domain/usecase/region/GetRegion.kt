package com.pokeapp.domain.usecase.region

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.model.region.Region
import com.pokeapp.domain.repository.RegionRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */

class GetRegion(
    private val repository: RegionRepository,
    scope: CoroutineScope
) : UseCase<List<Region>, Unit>(scope) {

    override fun run(params: Unit?) = repository.getRegion()
}