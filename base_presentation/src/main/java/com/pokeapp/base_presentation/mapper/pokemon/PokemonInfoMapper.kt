package com.pokeapp.base_presentation.mapper.pokemon

import com.pokeapp.base_presentation.mapper.ability.AbilityMapper
import com.pokeapp.base_presentation.mapper.base.PresentationMapper
import com.pokeapp.base_presentation.mapper.evolution.EvolutionMapper
import com.pokeapp.base_presentation.mapper.move.MoveMapper
import com.pokeapp.base_presentation.mapper.stats.StatsMapper
import com.pokeapp.base_presentation.mapper.type.TypeMapper
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.domain.model.pokemon.PokemonInfo

/*
 * Created by Filipi Andrade Rocha on 22/01/2021.
 */

object PokemonInfoMapper : PresentationMapper<PokemonInfoBinding, PokemonInfo> {

    override fun toDomain(presentation: PokemonInfoBinding) = PokemonInfo(
        id = presentation.id,
        name = presentation.name,
        photo = presentation.photo,
        photoShiny = presentation.photoShiny,
        generationName = presentation.generationName,
        description = presentation.description,
        height = presentation.height,
        baseExperience = presentation.baseExperience,
        weight = presentation.weight,
        types = TypeMapper.listToDomain(presentation.types),
        abilities = AbilityMapper.listToDomain(presentation.abilities),
        moves = MoveMapper.listToDomain(presentation.moves),
        stats = StatsMapper.listToDomain(presentation.stats),
        evolution = EvolutionMapper.listToDomain(presentation.evolution)
    )

    override fun fromDomain(domain: PokemonInfo) = PokemonInfoBinding(
        id = domain.id,
        name = domain.name,
        photo = domain.photo,
        photoShiny = domain.photoShiny,
        generationName = domain.generationName,
        description = domain.description,
        height = domain.height,
        baseExperience = domain.baseExperience,
        weight = domain.weight,
        types = TypeMapper.listFromDomain(domain.types),
        abilities = AbilityMapper.listFromDomain(domain.abilities),
        moves = MoveMapper.listFromDomain(domain.moves),
        stats = StatsMapper.listFromDomain(domain.stats),
        evolution = EvolutionMapper.listFromDomain(domain.evolution)
    )
}