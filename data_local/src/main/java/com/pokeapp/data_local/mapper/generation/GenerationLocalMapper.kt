package com.pokeapp.data_local.mapper.generation

import com.pokeapp.data_local.mapper.base.DataLocalMapper
import com.pokeapp.data_local.model.generation.GenerationLocal
import com.pokeapp.domain.model.generation.Generation

/*
 * Created by Filipi Andrade Rocha on 26/01/2021.
 */

object GenerationLocalMapper : DataLocalMapper<GenerationLocal, Generation> {

    override fun toLocal(domain: Generation) = GenerationLocal(
        id = domain.id,
        name = domain.name,
        region = domain.region
    )

    override fun fromLocal(local: GenerationLocal) = Generation(
        id = local.id,
        name = local.name,
        region = local.region
    )
}