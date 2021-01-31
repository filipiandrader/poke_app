package com.dexapp.di

import com.dexapp.presentation_favoridex.FavoridexInfoViewModel
import com.dexapp.presentation_favoridex.FavoridexViewModel
import com.dexapp.presentation_pokedex.info.PokemonInfoViewModel
import com.dexapp.presentation_pokedex.pokemon.PokemonViewModel
import com.dexapp.presentation_region.RegionInfoViewModel
import com.dexapp.presentation_region.RegionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
 * Created by Filipi Andrade Rocha on 18/01/2021.
 */

val presentationModule = module {

    viewModel { RegionViewModel() }

    viewModel { RegionInfoViewModel() }

    viewModel { PokemonViewModel() }

    viewModel { PokemonInfoViewModel() }

    viewModel { FavoridexViewModel() }

    viewModel { FavoridexInfoViewModel() }
}
