package com.pokeapp.data_remote.mapper.pokemon

import com.pokeapp.data_remote.mapper.stats.StatsMapper
import com.pokeapp.data_remote.mapper.type.TypeMapper
import com.pokeapp.data_remote.mapper.ability.AbilityMapper
import com.pokeapp.data_remote.mapper.evolution.EvolutionMapper
import com.pokeapp.data_remote.mapper.move.MoveMapper
import com.pokeapp.data_remote.model.pokemon.PokemonInfoResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.pokemon.PokemonInfo

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object PokemonInfoMapper : DataRemoteMapper<PokemonInfoResponse, PokemonInfo>() {

    override fun toDomain(data: PokemonInfoResponse) = PokemonInfo(
        id = data.id ?: -1,
        name = data.name ?: "",
        photo = data.photo ?: "",
        photoShiny = data.photoShiny ?: "",
        generationName = data.generationName ?: "",
        description = data.description ?: "",
        height = data.height ?: -1,
        baseExperience = data.baseExperience ?: -1,
        weight = data.weight ?: -1,
        types = TypeMapper.listToDomain(data.types),
        abilities = AbilityMapper.listToDomain(data.abilities),
        moves = MoveMapper.listToDomain(data.moves),
        stats = StatsMapper.listToDomain(data.stats),
        evolution = EvolutionMapper.listToDomain(data.evolutions)
    )
}