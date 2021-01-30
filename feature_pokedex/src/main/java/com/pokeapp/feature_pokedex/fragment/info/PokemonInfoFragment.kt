package com.pokeapp.feature_pokedex.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.delegateproperties.navDirections
import com.pokeapp.base_feature.util.enums.PokemonTypeEnum
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.feature_pokedex.R
import com.pokeapp.feature_pokedex.adapter.PokemonInfoViewPagerAdapter
import com.pokeapp.feature_pokedex.databinding.FragmentPokemonInfoBinding
import com.pokeapp.feature_pokedex.navigation.PokemonInfoNavigation
import com.pokeapp.presentation_pokedex.info.PokemonInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class PokemonInfoFragment : BaseFragment() {

    private val viewModel: PokemonInfoViewModel by viewModel()
    private val navigation: PokemonInfoNavigation by navDirections()

    private lateinit var binding: FragmentPokemonInfoBinding
    private lateinit var pokemonInfo: PokemonInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        changeStatusBarColor(getColor())
        binding = FragmentPokemonInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getColor() = requireContext().getPokemonColor(navigation.pokemon.types[0].name)

    override fun setupView() {
        viewModel.getPokemonInfo(navigation.pokemon.id)

        binding.apply {
            navigationIconImageView.setOnClickListener { navigation.navigateToPokedex() }

            pokemonInfoAppBarLayout.setBackgroundColor(getColor())
            pokemonInfoCollapsingToolbarLayout.setColorFilter(getColor())

            setupLikeIcon(navigation.pokemon.liked)

            pokemonInfoNameTextView.putText(navigation.pokemon.name.formatPokemonName())
            pokemonInfoIDTextView.putText(navigation.pokemon.id.formatPokemonNumber())

            pokemonInfoNormalImageView.loadUrl(navigation.pokemon.photo)
            pokemonInfoShinyImageView.loadUrl(navigation.pokemon.photoShiny)

            navigation.pokemon.types.getOrNull(0).let { firstType ->
                pokemonInfoType3TextView.putText(PokemonTypeEnum.match(firstType?.name))
                pokemonInfoType3TextView.setVisible(firstType != null)
            }

            navigation.pokemon.types.getOrNull(1).let { secondType ->
                pokemonInfoType2TextView.putText(PokemonTypeEnum.match(secondType?.name))
                pokemonInfoType2TextView.setVisible(secondType != null)
            }

            navigation.pokemon.types.getOrNull(2).let { thirdType ->
                pokemonInfoType1TextView.putText(PokemonTypeEnum.match(thirdType?.name))
                pokemonInfoType1TextView.setVisible(thirdType != null)
            }

            pokemonInfoLikedImageView.setOnClickListener { viewModel.doLikePokemon(pokemonInfo) }
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.fetchPokemonInfoViewState.onPostValue(owner) {
            setupInfo(it)
        }

        viewModel.fetchLikePokemonViewState.onPostValue(owner) {
            pokemonInfo.liked = !pokemonInfo.liked
            setupLikeIcon(pokemonInfo.liked)
        }
    }

    private fun setupInfo(pokemon: PokemonInfoBinding) {
        pokemonInfo = pokemon
        binding.apply {
            pokemonInfoViewPager.adapter =
                PokemonInfoViewPagerAdapter(
                    requireActivity().supportFragmentManager,
                    requireContext(),
                    pokemon
                )
            pokemonInfoTabLayout.setupWithViewPager(pokemonInfoViewPager)
            pokemonInfoViewPager.setVisible()
        }
    }

    private fun setupLikeIcon(liked: Boolean) {
        if (liked) {
            binding.pokemonInfoLikedImageView.setImageDrawable(
                getDrawableRes(R.drawable.ic_favourite)
            )
        } else {
            binding.pokemonInfoLikedImageView.setImageDrawable(
                getDrawableRes(R.drawable.ic_not_favourite)
            )
        }
    }
}
