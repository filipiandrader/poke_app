package com.pokeapp.feature_pokedex.fragment.info

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pokeapp.base_feature.util.enums.PokemonTypeEnum
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.PokemonInfoBinding
import com.pokeapp.feature_pokedex.R
import com.pokeapp.feature_pokedex.adapter.PokemonDetailsViewPagerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_info.*


class PokemonInfoFragment : Fragment() {

//    private val viewModel: PokemonInfoViewModel by viewModel()

    private lateinit var pokemon: PokemonInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_pokemon_info, container, false)

    /*private fun creatingObservers() {
        viewModel.getStateInfo().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.LOADING -> {
                    isClickableImageView(false)
                    pokemonDetailsProgressBar.setVisible(true)
                    pokemonDetailsViewPager.setVisible(false)
                    pokemonDetailsErrorTextView.setVisible(false)
                }
                State.SUCCESS -> {
                    pokemonDetailsSwipeRefreshLayout.setRefresh(false)
                    viewModel.getStateInfo().value?.data?.let {
                        bindInfo(it)
                    }
                }
                State.FAILURE -> {
                    pokemonDetailsErrorTextView.putText("Ocorreu um erro ao obter informações sobre ${pokemon.name} :(")
                    pokemonDetailsErrorTextView.setVisible(true)
                    pokemonDetailsProgressBar.setVisible(false)
                }
                else -> {
                    // IGNORE
                }
            }
        })

        viewModel.getState().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.SUCCESS -> showToast()
                State.FAILURE -> {}*//*longToast("Ocorreu um erro ao favoritar/desfavoritar ${pokemon.name} :(")*//*
                else -> {
                    // IGNORE
                }
            }
        })
    }*/

    override fun onStart() {
        super.onStart()

        // BACK BUTTON
        navigationIconImageView.setOnClickListener { findNavController().navigateUp() }
    }

    private fun isClickableImageView(isClickable: Boolean) {
        pokemonDetailsFavouriteImageView.isClickable = isClickable
    }

    private fun bindInfo(pokemon: PokemonInfoBinding) {
        this.pokemon.height = pokemon.height
        this.pokemon.weight = pokemon.weight
        this.pokemon.generationName = pokemon.generationName
        this.pokemon.description = pokemon.description
        this.pokemon.baseExperience = pokemon.baseExperience
        this.pokemon.abilities = pokemon.abilities
        this.pokemon.moves = pokemon.moves
        this.pokemon.stats = pokemon.stats
        this.pokemon.evolves = pokemon.evolves

        pokemonDetailsViewPager.adapter =
            PokemonDetailsViewPagerAdapter(
                requireActivity().supportFragmentManager,
                requireContext(),
                this.pokemon
            )
        pokemonDetailsTabLayout.setupWithViewPager(pokemonDetailsViewPager)

        pokemonDetailsProgressBar.setVisible(false)
        pokemonDetailsViewPager.setVisible(true)
        isClickableImageView(true)
    }

    private fun showToast() {
        if (pokemon.favourite) {
//            longToast("${pokemon.name} foi favoritado(a) :)")
        } else {
//            longToast("${pokemon.name} foi desfavoritado(a) :(")
        }

        setFavouriteIconCorrectly()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemon = checkNotNull(arguments?.getParcelable("pokemon"))
//        viewModel.getPokemonInfo(pokemon.id)

        pokemonDetailsNameTextView.putText(pokemon.name)
        val id = when (pokemon.id) {
            in 1..9 -> {
                "#00${pokemon.id}"
            }
            in 10..99 -> {
                "#0${pokemon.id}"
            }
            else -> {
                "#${pokemon.id}"
            }
        }
        pokemonDetailsIDTextView.putText(id)

        val color = requireContext().getPokemonColor(pokemon.types)
        pokemonDetailsAppBarLayout.setBackgroundColor(color)
        pokemonDetailsCollapsingToolbarLayout.contentScrim?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        activity?.window?.statusBarColor = color

        Picasso.get().load(pokemon.photo).into(pokemonDetailsNormalImageView)
        Picasso.get().load(pokemon.photoShiny).into(pokemonDetailsShinyImageView)

        pokemon.types.getOrNull(0).let { firstType ->
            pokemonDetailsType3TextView.putText(PokemonTypeEnum.match(firstType?.name))
            pokemonDetailsType3TextView.setVisible(firstType != null)
        }

        pokemon.types.getOrNull(1).let { secondType ->
            pokemonDetailsType2TextView.putText(PokemonTypeEnum.match(secondType?.name))
            pokemonDetailsType2TextView.setVisible(secondType != null)
        }

        pokemon.types.getOrNull(2).let { thirdType ->
            pokemonDetailsType1TextView.putText(PokemonTypeEnum.match(thirdType?.name))
            pokemonDetailsType1TextView.setVisible(thirdType != null)
        }

        setFavouriteIconCorrectly()
        pokemonDetailsFavouriteImageView.setOnClickListener { doFavouritePokemon() }

        pokemonDetailsSwipeRefreshLayout.setOnRefreshListener {
            pokemonDetailsSwipeRefreshLayout.setRefresh(true)
//            viewModel.getPokemonInfo(pokemon.id)
        }
    }

    private fun setFavouriteIconCorrectly() {
        if (pokemon.favourite) {
            pokemonDetailsFavouriteImageView.setImageDrawable(ContextCompat.getDrawable( requireContext(), R.drawable.ic_favourite))
        } else {
            pokemonDetailsFavouriteImageView.setImageDrawable(ContextCompat.getDrawable( requireContext(), R.drawable.ic_not_favourite))
        }
    }

    private fun doFavouritePokemon() {
        pokemon.favourite = !pokemon.favourite
//        viewModel.doFavouritePokemon(pokemon)
    }
}
