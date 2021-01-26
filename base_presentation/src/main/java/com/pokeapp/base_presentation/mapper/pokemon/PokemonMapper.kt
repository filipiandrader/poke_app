package com.pokeapp.base_presentation.mapper.pokemon

import com.pokeapp.base_presentation.mapper.base.PresentationMapper
import com.pokeapp.base_presentation.mapper.type.TypeMapper
import com.pokeapp.base_presentation.model.pokemon.PokemonBinding
import com.pokeapp.domain.model.pokemon.Pokemon

/*
 * Created by Filipi Andrade Rocha on 23/01/2021.
 */

object PokemonMapper : PresentationMapper<PokemonBinding, Pokemon> {

    fun lisToDomain(presentation: List<PokemonBinding>) = presentation.map { toDomain(it) }

    fun listFromDomain(domain: List<Pokemon>) = domain.map { fromDomain(it) }

    override fun toDomain(presentation: PokemonBinding) = Pokemon(
        id = presentation.id,
        name = presentation.name,
        photo = presentation.photo,
        photoShiny = presentation.photoShiny,
        generationName = presentation.generationName,
        types = TypeMapper.listToDomain(presentation.types),
        liked = presentation.liked
    )

    override fun fromDomain(domain: Pokemon) = PokemonBinding(
        id = domain.id,
        name = domain.name,
        photo = domain.photo,
        photoShiny = domain.photoShiny,
        generationName = domain.generationName,
        types = TypeMapper.listFromDomain(domain.types),
        liked = domain.liked
    )
}