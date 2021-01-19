package com.pokeapp.di

import com.pokeapp.domain.core.ThreadContextProvider
import com.pokeapp.domain.interactor.GetRegion
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
}
