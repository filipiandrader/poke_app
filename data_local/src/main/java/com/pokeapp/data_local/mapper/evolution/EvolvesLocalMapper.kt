package com.pokeapp.data_local.mapper.evolution

import com.pokeapp.data_local.mapper.base.DataLocalMapper
import com.pokeapp.data_local.model.evolution.EvolutionLocal
import com.pokeapp.domain.model.evolution.Evolution

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object EvolvesLocalMapper : DataLocalMapper<EvolutionLocal, Evolution> {

    override fun toLocal(domain: Evolution) = EvolutionLocal(
        name = domain.name,
        photo = domain.photo
    )

    override fun fromLocal(local: EvolutionLocal) = Evolution(
        name = local.name,
        photo = local.photo
    )
}