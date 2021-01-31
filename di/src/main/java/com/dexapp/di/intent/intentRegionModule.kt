package com.dexapp.di.intent

import androidx.fragment.app.Fragment
import com.dexapp.feature_pokedex.navigation.PokedexNavigation
import com.dexapp.feature_pokedex.navigation.PokemonInfoNavigation
import com.dexapp.feature_region.navigation.info.RegionInfoNavigation
import com.dexapp.feature_region.navigation.main.RegionNavigation
import com.dexapp.intent.navigation.pokedex.PokedexNavigationImpl
import com.dexapp.intent.navigation.pokedex.PokemonInfoNavigationImpl
import com.dexapp.intent.navigation.region.RegionInfoNavigationImpl
import com.dexapp.intent.navigation.region.RegionNavigationImpl
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 19/01/2021.
 */
 
val intentRegionModule = module {

    factory<RegionNavigation> { (fragment: Fragment) ->
        RegionNavigationImpl(fragment)
    }

    factory<RegionInfoNavigation> { (fragment: Fragment) ->
        RegionInfoNavigationImpl(fragment)
    }

    factory<PokedexNavigation> { (fragment: Fragment) ->
        PokedexNavigationImpl(fragment)
    }

    factory<PokemonInfoNavigation> { (fragment: Fragment) ->
        PokemonInfoNavigationImpl(fragment)
    }
}