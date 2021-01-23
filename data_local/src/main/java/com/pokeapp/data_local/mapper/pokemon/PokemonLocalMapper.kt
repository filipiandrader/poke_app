package com.pokeapp.data_local.mapper.pokemon

import com.pokeapp.data_local.mapper.ability.AbilityLocalMapper.toAbilityList
import com.pokeapp.data_local.mapper.ability.AbilityLocalMapper.toAbilityLocalList
import com.pokeapp.data_local.mapper.evolution.EvolvesLocalMapper.toEvolvesList
import com.pokeapp.data_local.mapper.evolution.EvolvesLocalMapper.toEvolvesLocalList
import com.pokeapp.data_local.mapper.moves.MovesLocalMapper.toMovesList
import com.pokeapp.data_local.mapper.moves.MovesLocalMapper.toMovesLocalList
import com.pokeapp.data_local.mapper.stats.StatsLocalMapper.toStatsList
import com.pokeapp.data_local.mapper.stats.StatsLocalMapper.toStatsLocalList
import com.pokeapp.data_local.mapper.type.TypeLocalMapper.toTypeList
import com.pokeapp.data_local.mapper.type.TypeLocalMapper.toTypeLocalList
import com.pokeapp.data_local.model.pokemon.PokemonLocal
import com.pokeapp.domain.model.pokemon.Pokemon
import com.pokeapp.domain.model.pokemon.PokemonInfo

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object PokemonLocalMapper {

    fun toDomainList(pokemonsLocal: List<PokemonLocal>) = pokemonsLocal.map { toDomain(it) }

    private fun toDomain(pokemonLocal: PokemonLocal) = Pokemon(
            id = pokemonLocal.id,
            name = pokemonLocal.name,
            photo = pokemonLocal.photo,
            photoShiny = pokemonLocal.photoShiny,
            generationName = pokemonLocal.generationName,
            types = toTypeList(pokemonLocal.types).toMutableList(),
            liked = pokemonLocal.liked
    )

    fun toDomainInfo(pokemonLocal: PokemonLocal) = PokemonInfo(
            id = pokemonLocal.id,
            name = pokemonLocal.name,
            photo = pokemonLocal.photo,
            photoShiny = pokemonLocal.photoShiny,
            generationName = pokemonLocal.generationName,
            description = pokemonLocal.description,
            height = pokemonLocal.height,
            baseExperience = pokemonLocal.baseExperience,
            weight = pokemonLocal.weight,
            types = toTypeList(pokemonLocal.types).toMutableList(),
            abilities = toAbilityList(pokemonLocal.abilities).toMutableList(),
            moves = toMovesList(pokemonLocal.moves).toMutableList(),
            stats = toStatsList(pokemonLocal.stats).toMutableList(),
            evolution = toEvolvesList(pokemonLocal.evolves).toMutableList(),
            liked = pokemonLocal.liked
    )

    fun toSaveLocal(pokemon: PokemonInfo) = PokemonLocal(
            id = pokemon.id,
            name = pokemon.name,
            photo = pokemon.photo,
            photoShiny = pokemon.photoShiny,
            generationName = pokemon.generationName,
            description = pokemon.description,
            height = pokemon.height,
            baseExperience = pokemon.baseExperience,
            weight = pokemon.weight,
            types = toTypeLocalList(pokemon.types).toMutableList(),
            abilities = toAbilityLocalList(pokemon.abilities).toMutableList(),
            moves = toMovesLocalList(pokemon.moves).toMutableList(),
            stats = toStatsLocalList(pokemon.stats).toMutableList(),
            evolves = toEvolvesLocalList(pokemon.evolution).toMutableList(),
            liked = true
    )
}