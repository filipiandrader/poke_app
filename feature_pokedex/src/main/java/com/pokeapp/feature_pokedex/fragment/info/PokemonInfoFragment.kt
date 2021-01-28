package com.pokeapp.feature_pokedex.fragment.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.delegateproperties.navDirections
import com.pokeapp.base_feature.util.enums.PokemonTypeEnum
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.feature_pokedex.R
import com.pokeapp.feature_pokedex.adapter.PokemonDetailsViewPagerAdapter
import com.pokeapp.feature_pokedex.databinding.FragmentPokemonInfoBinding
import com.pokeapp.feature_pokedex.navigation.PokemonInfoNavigation
import com.pokeapp.presentation_pokedex.info.PokemonInfoViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_info.*
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

            pokemonDetailsAppBarLayout.setBackgroundColor(getColor())
            pokemonDetailsCollapsingToolbarLayout.setColorFilter(getColor())
            activity?.window?.statusBarColor = getColor()

            setFavouriteIconCorrectly(navigation.pokemon.liked)

            pokemonDetailsNameTextView.putText(navigation.pokemon.name.formatPokemonName())
            pokemonDetailsIDTextView.putText(navigation.pokemon.id.formatPokemonNumber())

            pokemonDetailsNormalImageView.loadUrl(navigation.pokemon.photo)
            pokemonDetailsShinyImageView.loadUrl(navigation.pokemon.photoShiny)

            navigation.pokemon.types.getOrNull(0).let { firstType ->
                pokemonDetailsType3TextView.putText(PokemonTypeEnum.match(firstType?.name))
                pokemonDetailsType3TextView.setVisible(firstType != null)
            }

            navigation.pokemon.types.getOrNull(1).let { secondType ->
                pokemonDetailsType2TextView.putText(PokemonTypeEnum.match(secondType?.name))
                pokemonDetailsType2TextView.setVisible(secondType != null)
            }

            navigation.pokemon.types.getOrNull(2).let { thirdType ->
                pokemonDetailsType1TextView.putText(PokemonTypeEnum.match(thirdType?.name))
                pokemonDetailsType1TextView.setVisible(thirdType != null)
            }

            pokemonDetailsFavouriteImageView.setOnClickListener { doLikePokemon() }

            pokemonDetailsSwipeRefreshLayout.setOnRefreshListener {
                pokemonDetailsSwipeRefreshLayout.setRefresh(true)
                viewModel.getPokemonInfo(navigation.pokemon.id)
            }
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.fetchPokemonInfoViewState.onPostValue(owner) {
            setupInfo(it)
        }

        viewModel.fetchLikePokemonViewState.onPostValue(owner) {
            pokemonInfo.liked = !pokemonInfo.liked
            setFavouriteIconCorrectly(pokemonInfo.liked)
        }
    }

    private fun setupInfo(pokemon: PokemonInfoBinding) {
        pokemonInfo = pokemon

        binding.apply {
            pokemonDetailsViewPager.adapter =
                PokemonDetailsViewPagerAdapter(
                    requireActivity().supportFragmentManager,
                    requireContext(),
                    pokemon
                )
            pokemonDetailsTabLayout.setupWithViewPager(pokemonDetailsViewPager)

            pokemonDetailsProgressBar.setGone()
            pokemonDetailsViewPager.setVisible()
        }
    }

    private fun setFavouriteIconCorrectly(liked: Boolean) {
        if (liked) {
            pokemonDetailsFavouriteImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_favourite
                )
            )
        } else {
            pokemonDetailsFavouriteImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_not_favourite
                )
            )
        }
    }

    private fun doLikePokemon() {
        viewModel.doLikePokemon(pokemonInfo)
    }
}
