package com.dexapp.data_local.mapper.pokemon

import com.dexapp.data_local.mapper.ability.AbilityLocalMapper
import com.dexapp.data_local.mapper.evolution.EvolvesLocalMapper
import com.dexapp.data_local.mapper.moves.MovesLocalMapper
import com.dexapp.data_local.mapper.stats.StatsLocalMapper
import com.dexapp.data_local.mapper.type.TypeLocalMapper
import com.dexapp.data_local.mapper.base.DataLocalMapper
import com.dexapp.data_local.model.pokemon.PokemonLocal
import com.dexapp.domain.model.pokemon.PokemonInfo

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object PokemonLocalMapper : DataLocalMapper<PokemonLocal, PokemonInfo> {

    override fun toLocal(domain: PokemonInfo) = PokemonLocal(
        id = domain.id,
        name = domain.name,
        photo = domain.photo,
        photoShiny = domain.photoShiny,
        generationName = domain.generationName,
        description = domain.description,
        height = domain.height,
        baseExperience = domain.baseExperience,
        weight = domain.weight,
        types = TypeLocalMapper.toLocal(domain.types).toMutableList(),
        abilities = AbilityLocalMapper.toLocal(domain.abilities).toMutableList(),
        moves = MovesLocalMapper.toLocal(domain.moves).toMutableList(),
        stats = StatsLocalMapper.toLocal(domain.stats).toMutableList(),
        evolves = EvolvesLocalMapper.toLocal(domain.evolution).toMutableList(),
        liked = true
    )

    override fun fromLocal(local: PokemonLocal) = PokemonInfo(
        id = local.id,
        name = local.name,
        photo = local.photo,
        photoShiny = local.photoShiny,
        generationName = local.generationName,
        description = local.description,
        height = local.height,
        baseExperience = local.baseExperience,
        weight = local.weight,
        types = TypeLocalMapper.fromLocal(local.types).toMutableList(),
        abilities = AbilityLocalMapper.fromLocal(local.abilities).toMutableList(),
        moves = MovesLocalMapper.fromLocal(local.moves).toMutableList(),
        stats = StatsLocalMapper.fromLocal(local.stats).toMutableList(),
        evolution = EvolvesLocalMapper.fromLocal(local.evolves).toMutableList(),
        liked = local.liked
    )
}