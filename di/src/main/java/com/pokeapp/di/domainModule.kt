package com.pokeapp.di

import com.pokeapp.domain.core.ThreadContextProvider
import com.pokeapp.domain.usecase.generation.*
import com.pokeapp.domain.usecase.like.LikePokemon
import com.pokeapp.domain.usecase.pokedex.GetFavoridex
import com.pokeapp.domain.usecase.pokedex.GetPokedex
import com.pokeapp.domain.usecase.pokemon.GetPokemonInfo
import com.pokeapp.domain.usecase.region.GetRegion
import com.pokeapp.domain.usecase.region.GetRegionInfo
import com.pokeapp.domain.usecase.type.*
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
        GetFavoridex(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetPokemonInfo(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        LikePokemon(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetType(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetTypeLocal(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetPokemonByType(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetPokemonLikedByType(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        SaveTypeLocal(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetGeneration(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetPokemonByGeneration(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetPokemonLikedByGeneration(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        SaveGenerationLocal(get(), scope)
    }

    factory { (scope: CoroutineScope) ->
        GetGenerationLocal(get(), scope)
    }
}
