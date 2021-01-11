package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.PokemonInfoResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Pokemon

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object PokemonMapper : DataRemoteMapper<PokemonInfoResponse, Pokemon>() {

    override fun toDomain(data: PokemonInfoResponse) = Pokemon(
        id = data.id ?: -1,
        name = data.name ?: "",
        photo = data.photo ?: "",
        photoShiny = data.photoShiny ?: "",
        generation = data.generation ?: "",
        about = data.about ?: "",
        height = data.height ?: -1,
        baseExperience = data.baseExperience ?: -1,
        weight = data.weight ?: -1,
        types = TypeMapper.toDomain(data.types),
        abilities = AbilityMapper.toDomain(data.abilities),
        moves = MoveMapper.toDomain(data.moves),
        stats = StatsMapper.toDomain(data.stats),
        evolves = EvolvesMapper.toDomain(data.evolves)
    )
}