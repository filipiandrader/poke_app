package com.pokeapp.presentation_pokedex.info

import androidx.lifecycle.ViewModel
import com.pokeapp.base_presentation.mapper.pokemon.PokemonInfoMapper
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.base_presentation.utils.extensions.*
import com.pokeapp.domain.interactor.pokemon.GetPokemonInfo
import org.koin.core.KoinComponent

/**
 * Created by Filipi Andrade on 31/03/2020
 */
class PokemonInfoViewModel : ViewModel(), KoinComponent {

    private val getPokemonInfo: GetPokemonInfo by useCase()

    private val _fetchPokemonInfoViewState by viewState<PokemonInfoBinding>()
    private val _fetchLikePokemonViewState by viewState<Unit>()

    val fetchPokemonInfoViewState = _fetchPokemonInfoViewState.asLiveData()
    val fetchLikePokemonViewState = _fetchLikePokemonViewState.asLiveData()

    fun getPokemonInfo(id: Int) {
        _fetchPokemonInfoViewState.postLoading()

        getPokemonInfo(
            params = GetPokemonInfo.Params(id),
            onSuccess = { _fetchPokemonInfoViewState.postSuccess(PokemonInfoMapper.fromDomain(it)) },
            onError = { _fetchPokemonInfoViewState.postError(it) }
        )
    }

    fun doFavouritePokemon(pokemon: PokemonInfoBinding) {
        _fetchLikePokemonViewState.postLoading()
        _fetchLikePokemonViewState.postSuccess(Unit)

        /*dataSource.doFavouritePokemon(pokemon,
                onSuccess = {
                    mState.postValue(ViewState.success())
                },
                onFailure = {
                    mState.postValue(ViewState.failure())
                })*/
    }
}