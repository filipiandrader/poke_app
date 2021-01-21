package com.pokeapp.base_presentation.mapper.region.pokedex

import com.pokeapp.base_presentation.mapper.base.PresentationMapper
import com.pokeapp.base_presentation.mapper.region.type.TypeMapper
import com.pokeapp.base_presentation.model.PokemonBinding
import com.pokeapp.domain.model.Pokemon

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

object PokedexMapper : PresentationMapper<PokemonBinding, Pokemon> {

    fun listFromDomain(pokedex: List<Pokemon>) = pokedex.map { fromDomain(it) }

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