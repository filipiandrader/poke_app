package com.pokeapp.di

import com.pokeapp.domain.core.ThreadContextProvider
import com.pokeapp.domain.interactor.pokedex.GetPokedex
import com.pokeapp.domain.interactor.region.GetRegion
import com.pokeapp.domain.interactor.region.GetRegionInfo
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
}
