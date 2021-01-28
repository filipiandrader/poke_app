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
        types = TypeMapper.toDomain(presentation.types),
        abilities = AbilityMapper.toDomain(presentation.abilities),
        moves = MoveMapper.toDomain(presentation.moves),
        stats = StatsMapper.toDomain(presentation.stats),
        evolution = EvolutionMapper.toDomain(presentation.evolution)
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
        types = TypeMapper.fromDomain(domain.types),
        abilities = AbilityMapper.fromDomain(domain.abilities),
        moves = MoveMapper.fromDomain(domain.moves),
        stats = StatsMapper.fromDomain(domain.stats),
        evolution = EvolutionMapper.fromDomain(domain.evolution)
    )
}