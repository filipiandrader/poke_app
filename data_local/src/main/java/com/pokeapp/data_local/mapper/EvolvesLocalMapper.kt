package com.pokeapp.data_local.mapper

import com.pokeapp.data_local.model.SpeciesLocal
import com.pokeapp.domain.model.Species

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object EvolvesLocalMapper {

    fun toEvolvesList(evolvesLocal: List<SpeciesLocal>) = evolvesLocal.map { toEvolves(it) }

    private fun toEvolves(evolveLocal: SpeciesLocal) = Species(
            name = evolveLocal.name,
            photo = evolveLocal.photo
    )

    fun toEvolvesLocalList(evolves: List<Species>) = evolves.map { toEvolvesLocal(it) }

    private fun toEvolvesLocal(evolve: Species) = SpeciesLocal(
            name = evolve.name,
            photo = evolve.photo
    )
}