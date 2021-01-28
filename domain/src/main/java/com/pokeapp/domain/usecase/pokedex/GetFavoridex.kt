package com.pokeapp.domain.usecase.pokedex

import com.pokeapp.domain.core.UseCase
import com.pokeapp.domain.model.pokemon.PokemonInfo
import com.pokeapp.domain.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope

/*
 * Created by Filipi Andrade Rocha on 27/01/2021.
 */

class GetFavoridex(
    private val pokemonRepository: PokemonRepository,
    scope: CoroutineScope
) : UseCase<List<PokemonInfo>?, Unit>(scope) {

    override fun run(params: Unit?) = pokemonRepository.getAllPokemonsLiked()
}