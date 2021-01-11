package com.pokeapp.data_local.mapper

import com.pokeapp.data_local.mapper.TypeLocalMapper.toTypeList
import com.pokeapp.data_local.mapper.TypeLocalMapper.toTypeLocalList
import com.pokeapp.data_local.model.*
import com.pokeapp.domain.model.*

/*
 * Created by Filipi Andrade Rocha on 11/01/2021.
 */

object PokemonLocalMapper {

    fun toDomainList(pokemonsLocal: List<PokemonLocal>) = pokemonsLocal.map { toDomain(it) }

    fun toDomain(pokemonLocal: PokemonLocal) = Pokemon(
        id = pokemonLocal.id,
        name = pokemonLocal.name,
        photo = pokemonLocal.photo,
        photoShiny = pokemonLocal.photoShiny,
        generation = pokemonLocal.generation,
        about = pokemonLocal.about,
        height = pokemonLocal.height,
        baseExperience = pokemonLocal.baseExperience,
        weight = pokemonLocal.weight,
        types = toTypeList(pokemonLocal.types).toMutableList(),
        abilities = toAbilityList(pokemonLocal.abilities).toMutableList(),
        moves = toMovesList(pokemonLocal.moves).toMutableList(),
        stats = toStatsList(pokemonLocal.stats).toMutableList(),
        evolves = toEvolvesList(pokemonLocal.evolves).toMutableList(),
        liked = pokemonLocal.liked
    )

    fun toAbilityList(abilitiesLocal: List<AbilityLocal>) = abilitiesLocal.map { toAbility(it) }

    fun toAbility(abilityLocal: AbilityLocal) = Ability(
        name = abilityLocal.name
    )

    fun toMovesList(movesLocal: List<MoveLocal>) = movesLocal.map { toMoves(it) }

    fun toMoves(moveLocal: MoveLocal) = Move(
        name = moveLocal.name
    )

    fun toStatsList(statsLocal: List<StatsLocal>) = statsLocal.map { toStats(it) }

    fun toStats(statLocal: StatsLocal) = Stats(
        name = statLocal.name,
        baseState = statLocal.baseState
    )

    fun toEvolvesList(evolvesLocal: List<SpeciesLocal>) = evolvesLocal.map { toEvolves(it) }

    fun toEvolves(evolveLocal: SpeciesLocal) = Species(
        name = evolveLocal.name,
        photo = evolveLocal.photo
    )

    fun toSaveLocal(pokemon: Pokemon) = PokemonLocal(
        id = pokemon.id,
        name = pokemon.name,
        photo = pokemon.photo,
        photoShiny = pokemon.photoShiny,
        generation = pokemon.generation,
        about = pokemon.about,
        height = pokemon.height,
        baseExperience = pokemon.baseExperience,
        weight = pokemon.weight,
        types = toTypeLocalList(pokemon.types).toMutableList(),
        abilities = toAbilityLocalList(pokemon.abilities).toMutableList(),
        moves = toMovesLocalList(pokemon.moves).toMutableList(),
        stats = toStatsLocalList(pokemon.stats).toMutableList(),
        evolves = toEvolvesLocalList(pokemon.evolves).toMutableList(),
        liked = true
    )

    fun toAbilityLocalList(abilities: List<Ability>) = abilities.map { toAbilityLocal(it) }

    fun toAbilityLocal(ability: Ability) = AbilityLocal(
        name = ability.name
    )

    fun toMovesLocalList(moves: List<Move>) = moves.map { toMovesLocal(it) }

    fun toMovesLocal(move: Move) = MoveLocal(
        name = move.name
    )

    fun toStatsLocalList(stats: List<Stats>) = stats.map { toStatsLocal(it) }

    fun toStatsLocal(stat: Stats) = StatsLocal(
        name = stat.name,
        baseState = stat.baseState
    )

    fun toEvolvesLocalList(evolves: List<Species>) = evolves.map { toEvolvesLocal(it) }

    fun toEvolvesLocal(evolve: Species) = SpeciesLocal(
        name = evolve.name,
        photo = evolve.photo
    )
}