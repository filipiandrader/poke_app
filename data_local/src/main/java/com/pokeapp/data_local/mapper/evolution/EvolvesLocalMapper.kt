package com.pokeapp.data_local.mapper.evolution

import com.pokeapp.data_local.model.evolution.SpeciesLocal
import com.pokeapp.domain.model.evolution.Evolution

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object EvolvesLocalMapper {

    fun toEvolvesList(evolvesLocal: List<SpeciesLocal>) = evolvesLocal.map { toEvolves(it) }

    private fun toEvolves(evolveLocal: SpeciesLocal) = Evolution(
            name = evolveLocal.name,
            photo = evolveLocal.photo
    )

    fun toEvolvesLocalList(evolves: List<Evolution>) = evolves.map { toEvolvesLocal(it) }

    private fun toEvolvesLocal(evolve: Evolution) = SpeciesLocal(
            name = evolve.name,
            photo = evolve.photo
    )
}