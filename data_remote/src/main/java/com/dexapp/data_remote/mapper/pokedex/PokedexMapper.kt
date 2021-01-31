package com.dexapp.data_remote.mapper.pokedex

import com.dexapp.data_remote.mapper.pokemon.PokemonMapper
import com.dexapp.data_remote.mapper.type.TypeMapper
import com.dexapp.data_remote.mapper.generation.GenerationMapper
import com.dexapp.data_remote.model.pokedex.PokedexResponse
import com.dexapp.data_remote.utils.DataRemoteMapper
import com.dexapp.domain.model.pokedex.Pokedex

/*
 * Created by Filipi Andrade Rocha on 23/01/2021.
 */

object PokedexMapper : DataRemoteMapper<PokedexResponse, Pokedex>() {

    override fun toDomain(data: PokedexResponse) = Pokedex(
        pokedex = PokemonMapper.listToDomain(data.pokedex),
        generation = GenerationMapper.listToDomain(data.generation),
        type = TypeMapper.listToDomain(data.type)
    )
}