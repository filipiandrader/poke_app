package com.pokeapp.di

import com.pokeapp.domain.core.ThreadContextProvider
import com.pokeapp.domain.interactor.generation.GetGeneration
import com.pokeapp.domain.interactor.generation.GetPokemonByGeneration
import com.pokeapp.domain.interactor.pokedex.GetPokedex
import com.pokeapp.domain.interactor.region.GetRegion
import com.pokeapp.domain.interactor.region.GetRegionInfo
import com.pokeapp.domain.interactor.type.GetPokemonByType
import com.pokeapp.domain.interactor.type.GetType
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

val domainModule = module {

    single { ThreadContextProvider() }

    factory { (scope: CoroutineScope) ->
        GetRegion(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetRegionInfo(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetPokedex(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetType(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetPokemonByType(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetGeneration(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetPokemonByGeneration(get(), scope)
    }
}
