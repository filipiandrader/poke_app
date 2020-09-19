package com.pokeapp.ui.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.pokeapp.R
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.ui.fragments.details.abilities.AbilitiesFragment
import com.pokeapp.ui.fragments.details.about.AboutFragment
import com.pokeapp.ui.fragments.details.base_stats.BaseStatsFragment
import com.pokeapp.ui.fragments.details.evolution.EvolutionFragment
import com.pokeapp.ui.fragments.details.moves.MovesFragment

/**
 * Created by Filipi Andrade on 30/03/2020
 */

class PokemonDetailsViewPagerAdapter(supportFragmentManager: FragmentManager, context: Context, private val pokemon: Pokemon) :
        FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    data class Page(val title: String, val ctor: () -> Fragment)

    @Suppress("MoveLambdaOutsideParentheses")
    private val pages = listOf(
            Page(context.getString(R.string.pokemon_details_tab_1), { AboutFragment.newInstance(pokemon) }),
            Page(context.getString(R.string.pokemon_details_tab_2), { BaseStatsFragment.newInstance(pokemon) }),
            Page(context.getString(R.string.pokemon_details_tab_3), { EvolutionFragment.newInstance(pokemon) }),
            Page(context.getString(R.string.pokemon_details_tab_4), { MovesFragment.newInstance(pokemon) }),
            Page(context.getString(R.string.pokemon_details_tab_5), { AbilitiesFragment.newInstance(pokemon) })
    )

    override fun getItem(position: Int): Fragment {
        return pages[position].ctor()
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pages[position].title
    }

}