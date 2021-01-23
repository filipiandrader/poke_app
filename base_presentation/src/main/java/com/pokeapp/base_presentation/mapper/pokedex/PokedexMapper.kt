package com.pokeapp.base_presentation.mapper.pokedex

import com.pokeapp.base_presentation.mapper.base.PresentationMapper
import com.pokeapp.base_presentation.mapper.generation.GenerationMapper
import com.pokeapp.base_presentation.mapper.pokemon.PokemonMapper
import com.pokeapp.base_presentation.mapper.type.TypeMapper
import com.pokeapp.base_presentation.model.pokedex.PokedexBinding
import com.pokeapp.domain.model.pokedex.Pokedex

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

object PokedexMapper : PresentationMapper<PokedexBinding, Pokedex> {

    override fun toDomain(presentation: PokedexBinding) = Pokedex(
        pokedex = PokemonMapper.lisToDomain(presentation.pokedex),
        generation = GenerationMapper.listToDomain(presentation.generation),
        type = TypeMapper.listToDomain(presentation.type)
    )

    override fun fromDomain(domain: Pokedex) = PokedexBinding(
        pokedex = PokemonMapper.listFromDomain(domain.pokedex),
        generation = GenerationMapper.listFromDomain(domain.generation),
        type = TypeMapper.listFromDomain(domain.type)
    )
}