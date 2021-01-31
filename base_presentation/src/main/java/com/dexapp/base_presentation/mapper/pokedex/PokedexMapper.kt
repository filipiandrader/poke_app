package com.dexapp.base_presentation.mapper.pokedex

import com.dexapp.base_presentation.mapper.base.PresentationMapper
import com.dexapp.base_presentation.mapper.generation.GenerationMapper
import com.dexapp.base_presentation.mapper.pokemon.PokemonMapper
import com.dexapp.base_presentation.mapper.type.TypeMapper
import com.dexapp.base_presentation.model.pokedex.PokedexBinding
import com.dexapp.domain.model.pokedex.Pokedex

/*
 * Created by Filipi Andrade Rocha on 20/01/2021.
 */

object PokedexMapper : PresentationMapper<PokedexBinding, Pokedex> {

    override fun toDomain(presentation: PokedexBinding) = Pokedex(
        pokedex = PokemonMapper.toDomain(presentation.pokedex),
        generation = GenerationMapper.toDomain(presentation.generation),
        type = TypeMapper.toDomain(presentation.type)
    )

    override fun fromDomain(domain: Pokedex) = PokedexBinding(
        pokedex = PokemonMapper.fromDomain(domain.pokedex),
        generation = GenerationMapper.fromDomain(domain.generation),
        type = TypeMapper.fromDomain(domain.type)
    )
}