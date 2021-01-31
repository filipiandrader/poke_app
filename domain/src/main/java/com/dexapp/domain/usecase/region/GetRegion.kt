package com.dexapp.domain.usecase.region

import com.dexapp.domain.core.UseCase
import com.dexapp.domain.model.region.Region
import com.dexapp.domain.repository.RegionRepository
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