package com.pokeapp.domain.interactor

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.model.Region
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