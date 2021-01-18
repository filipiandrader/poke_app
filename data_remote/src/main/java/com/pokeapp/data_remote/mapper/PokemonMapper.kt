package com.pokeapp.data_remote.mapper

import com.pokeapp.data_remote.model.PokemonResponse
import com.pokeapp.data_remote.utils.DataRemoteMapper
import com.pokeapp.domain.model.Pokemon

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

object PokemonMapper : DataRemoteMapper<PokemonResponse, Pokemon>() {

    fun listToDomain(data: List<PokemonResponse>) = data.map { toDomain(it) }

    override fun toDomain(data: PokemonResponse) = Pokemon(
            id = data.id ?: -1,
            name = data.name ?: "",
            photo = data.photo ?: "",
            photoShiny = data.photoShiny ?: "",
            generationName = data.generationName ?: "",
            types = TypeMapper.listToDomain(data.types)
    )
}