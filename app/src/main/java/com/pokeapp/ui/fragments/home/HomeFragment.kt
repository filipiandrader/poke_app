package com.pokeapp.ui.fragments.home

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.pokeapp.R
import com.pokeapp.base_feature.util.extensions.PokemonColorUtil
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = PokemonColorUtil(view.context).convertColor(R.color.red)

        val colorPokedex = PokemonColorUtil(view.context).getCardViewColor("pokedex")
        homePokedexConstraintLayout.background.colorFilter = PorterDuffColorFilter(colorPokedex, PorterDuff.Mode.SRC_ATOP)
        homePokedexConstraintLayout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_pokedexFragment)
        }

        val colorFavoridex = PokemonColorUtil(view.context).getCardViewColor("favoridex")
        homeFavouriteConstraintLayout.background.colorFilter = PorterDuffColorFilter(colorFavoridex, PorterDuff.Mode.SRC_ATOP)
        homeFavouriteConstraintLayout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favouriteFragment)
        }

        val colorRegion = PokemonColorUtil(view.context).getCardViewColor("region")
        homeRegionConstraintLayout.background.colorFilter = PorterDuffColorFilter(colorRegion, PorterDuff.Mode.SRC_ATOP)
        homeRegionConstraintLayout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_regionFragment)
        }
    }
}
