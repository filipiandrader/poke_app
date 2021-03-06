package com.dexapp.feature_favoridex.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.dexapp.base_feature.core.BaseFragment
import com.dexapp.base_feature.util.delegateproperties.navDirections
import com.dexapp.base_feature.util.enums.PokemonTypeEnum
import com.dexapp.base_feature.util.extensions.*
import com.dexapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.dexapp.feature_favoridex.R
import com.dexapp.feature_favoridex.adapter.FavoridexInfoViewPagerAdapter
import com.dexapp.feature_favoridex.databinding.FragmentFavoridexInfoBinding
import com.dexapp.feature_favoridex.navigation.info.FavoridexInfoNavigation
import com.dexapp.presentation_favoridex.FavoridexInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoridexInfoFragment : BaseFragment() {

    private val navigation: FavoridexInfoNavigation by navDirections()
    private val viewModel: FavoridexInfoViewModel by viewModel()

    private lateinit var binding: FragmentFavoridexInfoBinding
    private lateinit var pokemonInfo: PokemonInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        changeStatusBarColor(getColor())
        binding = FragmentFavoridexInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getColor() = requireContext().getPokemonColor(navigation.pokemon.types[0].name)

    override fun setupView() {
        setupInfo(navigation.pokemon)
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.fetchLikePokemonViewState.onPostValue(owner) {
            pokemonInfo.liked = !pokemonInfo.liked
            setupLikeIcon(pokemonInfo.liked)
        }
    }

    private fun setupInfo(pokemon: PokemonInfoBinding) {
        pokemonInfo = pokemon
        setupLikeIcon(pokemon.liked)
        binding.apply {
            navigationIconImageView.setOnClickListener { navigation.navigateToFavoridex() }

            favoridexInfoAppBarLayout.setBackgroundColor(getColor())
            favoridexInfoCollapsingToolbarLayout.setColorFilter(getColor())

            favoridexInfoNameTextView.putText(navigation.pokemon.name.formatPokemonName())
            favoridexInfoIDTextView.putText(navigation.pokemon.id.formatPokemonNumber())

            favoridexInfoNormalImageView.loadUrl(navigation.pokemon.photo)
            favoridexInfoShinyImageView.loadUrl(navigation.pokemon.photoShiny)

            navigation.pokemon.types.getOrNull(0).let { firstType ->
                favoridexInfoType3TextView.putText(PokemonTypeEnum.match(firstType?.name))
                favoridexInfoType3TextView.setVisible(firstType != null)
            }

            navigation.pokemon.types.getOrNull(1).let { secondType ->
                favoridexInfoType2TextView.putText(PokemonTypeEnum.match(secondType?.name))
                favoridexInfoType2TextView.setVisible(secondType != null)
            }

            navigation.pokemon.types.getOrNull(2).let { thirdType ->
                favoridexInfoType1TextView.putText(PokemonTypeEnum.match(thirdType?.name))
                favoridexInfoType1TextView.setVisible(thirdType != null)
            }

            favoridexInfoLikedImageView.setOnClickListener { viewModel.doLikePokemon(pokemonInfo) }

            favoridexInfoViewPager.adapter = FavoridexInfoViewPagerAdapter(
                requireActivity().supportFragmentManager,
                requireContext(),
                pokemon
            )
            favoridexInfoTabLayout.setupWithViewPager(favoridexInfoViewPager)
            favoridexInfoViewPager.setVisible()
        }
    }

    private fun setupLikeIcon(liked: Boolean) {
        if (liked) {
            binding.favoridexInfoLikedImageView.setImageDrawable(
                getDrawableRes(R.drawable.ic_favourite)
            )
        } else {
            binding.favoridexInfoLikedImageView.setImageDrawable(
                getDrawableRes(R.drawable.ic_not_favourite)
            )
        }
    }
}