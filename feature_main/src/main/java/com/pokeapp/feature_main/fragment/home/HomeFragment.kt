package com.pokeapp.feature_main.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.delegateproperties.navDirections
import com.pokeapp.base_feature.util.extensions.addOnBackPressedCallback
import com.pokeapp.base_feature.util.extensions.convertColor
import com.pokeapp.base_feature.util.extensions.getCardViewColor
import com.pokeapp.base_feature.util.extensions.setColorFilter
import com.pokeapp.feature_main.R
import com.pokeapp.feature_main.databinding.FragmentHomeBinding
import com.pokeapp.feature_main.navigation.home.HomeNavigation
import kotlin.system.exitProcess

class HomeFragment : BaseFragment() {

    private val navigation: HomeNavigation by navDirections()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = requireContext().convertColor(R.color.red)
    }

    override fun setupView() {
        binding.run {
            val colorPokedex = requireContext().getCardViewColor("pokedex")
            homePokedexConstraintLayout.setColorFilter(colorPokedex)
            homePokedexConstraintLayout.setOnClickListener {
                navigation.navigateToPokedex()
            }

            val colorFavoridex = requireContext().getCardViewColor("favoridex")
            homeFavouriteConstraintLayout.setColorFilter(colorFavoridex)
            homeFavouriteConstraintLayout.setOnClickListener {
                navigation.navigateToFavoriDex()
            }

            val colorRegion = requireContext().getCardViewColor("region")
            homeRegionConstraintLayout.setColorFilter(colorRegion)
            homeRegionConstraintLayout.setOnClickListener {
                navigation.navigateToRegion()
            }
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        addOnBackPressedCallback(owner) {
            requireActivity().finish()
        }
    }
}
