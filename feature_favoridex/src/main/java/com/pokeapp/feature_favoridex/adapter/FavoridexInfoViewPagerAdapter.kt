package com.pokeapp.feature_favoridex.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.feature_favoridex.R
import com.pokeapp.feature_favoridex.fragment.info.abilities.AbilitiesFragment
import com.pokeapp.feature_favoridex.fragment.info.about.AboutFragment
import com.pokeapp.feature_favoridex.fragment.info.basestats.BaseStatsFragment
import com.pokeapp.feature_favoridex.fragment.info.evolution.EvolutionFragment
import com.pokeapp.feature_favoridex.fragment.info.moves.MovesFragment

/**
 * Created by Filipi Andrade on 30/03/2020
 */

class FavoridexInfoViewPagerAdapter(
    supportFragmentManager: FragmentManager,
    context: Context,
    private val pokemon: PokemonInfoBinding
) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val ctor: () -> Fragment)

    private val pages = getTabs(context)

    private fun getTabs(context: Context) = when (pokemon.evolution.isEmpty()) {
        true -> listOf(
            Page(context.getString(R.string.pokemon_details_tab_1)) {
                AboutFragment.newInstance(pokemon)
            },
            Page(context.getString(R.string.pokemon_details_tab_2)) {
                BaseStatsFragment.newInstance(pokemon)
            },
            Page(
                context.getString(R.string.pokemon_details_tab_4)
            ) { MovesFragment.newInstance(pokemon) },
            Page(
                context.getString(R.string.pokemon_details_tab_5)
            ) { AbilitiesFragment.newInstance(pokemon) }
        )
        false -> listOf(
            Page(context.getString(R.string.pokemon_details_tab_1)) {
                AboutFragment.newInstance(pokemon)
            },
            Page(context.getString(R.string.pokemon_details_tab_2)) {
                BaseStatsFragment.newInstance(pokemon)
            },
            Page(context.getString(R.string.pokemon_details_tab_3)) {
                EvolutionFragment.newInstance(pokemon)
            },
            Page(context.getString(R.string.pokemon_details_tab_4)) {
                MovesFragment.newInstance(pokemon)
            },
            Page(context.getString(R.string.pokemon_details_tab_5)) {
                AbilitiesFragment.newInstance(pokemon)
            }
        )
    }

    override fun getItem(position: Int): Fragment {
        return pages[position].ctor()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return pages[position].title
    }
}