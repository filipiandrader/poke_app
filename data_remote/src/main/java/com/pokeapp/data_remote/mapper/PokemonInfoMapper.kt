package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.PokemonInfoResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.PokemonInfo

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object PokemonInfoMapper : DataRemoteMapper<PokemonInfoResponse, PokemonInfo>() {

    override fun toDomain(data: PokemonInfoResponse) = PokemonInfo(
        id = data.id ?: -1,
        name = data.name ?: "",
        photo = data.photo ?: "",
        photoShiny = data.photoShiny ?: "",
        generation = data.generation ?: "",
        about = data.description ?: "",
        height = data.height ?: -1,
        baseExperience = data.baseExperience ?: -1,
        weight = data.weight ?: -1,
        types = TypeMapper.listToDomain(data.types),
        abilities = AbilityMapper.listToDomain(data.abilities),
        moves = MoveMapper.listToDomain(data.moves),
        stats = StatsMapper.listToDomain(data.stats),
        evolves = EvolvesMapper.listToDomain(listOf())
    )
}