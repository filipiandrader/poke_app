package com.pokeapp.ui.fragments.details

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pokeapp.R
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.PokemonBinding
import com.pokeapp.presentation.details.PokemonDetailsViewModel
import com.pokeapp.ui.fragments.PokemonDetailsViewPagerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class PokemonDetailsFragment : Fragment() {

    private val mViewModel: PokemonDetailsViewModel by viewModel()

    private lateinit var mPokemon: PokemonBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_pokemon_details, container, false)

    /*private fun creatingObservers() {
        mViewModel.getStateInfo().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.LOADING -> {
                    isClickableImageView(false)
                    pokemonDetailsProgressBar.setVisible(true)
                    pokemonDetailsViewPager.setVisible(false)
                    pokemonDetailsErrorTextView.setVisible(false)
                }
                State.SUCCESS -> {
                    pokemonDetailsSwipeRefreshLayout.setRefresh(false)
                    mViewModel.getStateInfo().value?.data?.let {
                        bindInfo(it)
                    }
                }
                State.FAILURE -> {
                    pokemonDetailsErrorTextView.putText("Ocorreu um erro ao obter informações sobre ${mPokemon.name} :(")
                    pokemonDetailsErrorTextView.setVisible(true)
                    pokemonDetailsProgressBar.setVisible(false)
                }
                else -> {
                    // IGNORE
                }
            }
        })

        mViewModel.getState().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.SUCCESS -> showToast()
                State.FAILURE -> {}*//*longToast("Ocorreu um erro ao favoritar/desfavoritar ${mPokemon.name} :(")*//*
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

    private fun bindInfo(pokemon: PokemonBinding) {
        mPokemon.height = pokemon.height
        mPokemon.weight = pokemon.weight
        mPokemon.generation = pokemon.generation
        mPokemon.about = pokemon.about
        mPokemon.base_experience = pokemon.base_experience
        mPokemon.abilities = pokemon.abilities
        mPokemon.moves = pokemon.moves
        mPokemon.stats = pokemon.stats
        mPokemon.evolves = pokemon.evolves

        pokemonDetailsViewPager.adapter = PokemonDetailsViewPagerAdapter(requireActivity().supportFragmentManager, requireContext(), mPokemon)
        pokemonDetailsTabLayout.setupWithViewPager(pokemonDetailsViewPager)

        pokemonDetailsProgressBar.setVisible(false)
        pokemonDetailsViewPager.setVisible(true)
        isClickableImageView(true)
    }

    private fun showToast() {
        if (mPokemon.favourite) {
//            longToast("${mPokemon.name} foi favoritado(a) :)")
        } else {
//            longToast("${mPokemon.name} foi desfavoritado(a) :(")
        }

        setFavouriteIconCorrectly()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPokemon = checkNotNull(arguments?.getSerializable("pokemon") as PokemonBinding)
        mViewModel.getPokemonInfo(mPokemon.id)

        pokemonDetailsNameTextView.putText(mPokemon.name)
        val id = when (mPokemon.id) {
            in 1..9 -> {
                "#00${mPokemon.id}"
            }
            in 10..99 -> {
                "#0${mPokemon.id}"
            }
            else -> {
                "#${mPokemon.id}"
            }
        }
        pokemonDetailsIDTextView.putText(id)

        val color = requireContext().getPokemonColor(mPokemon.types)
        pokemonDetailsAppBarLayout.setBackgroundColor(color)
        pokemonDetailsCollapsingToolbarLayout.contentScrim?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        activity?.window?.statusBarColor = color

        Picasso.get().load(mPokemon.photo).into(pokemonDetailsNormalImageView)
        Picasso.get().load(mPokemon.photo_shiny).into(pokemonDetailsShinyImageView)

        mPokemon.types.getOrNull(0).let { firstType ->
            pokemonDetailsType3TextView.putText(setTypeName(firstType?.name))
            pokemonDetailsType3TextView.setVisible(firstType != null)
        }

        mPokemon.types.getOrNull(1).let { secondType ->
            pokemonDetailsType2TextView.putText(setTypeName(secondType?.name))
            pokemonDetailsType2TextView.setVisible(secondType != null)
        }

        mPokemon.types.getOrNull(2).let { thirdType ->
            pokemonDetailsType1TextView.putText(setTypeName(thirdType?.name))
            pokemonDetailsType1TextView.setVisible(thirdType != null)
        }

        setFavouriteIconCorrectly()
        pokemonDetailsFavouriteImageView.setOnClickListener { doFavouritePokemon() }

        pokemonDetailsSwipeRefreshLayout.setOnRefreshListener {
            pokemonDetailsSwipeRefreshLayout.setRefresh(true)
            mViewModel.getPokemonInfo(mPokemon.id)
        }
    }

    private fun setFavouriteIconCorrectly() {
        if (mPokemon.favourite) {
            pokemonDetailsFavouriteImageView.setImageDrawable(ContextCompat.getDrawable( requireContext(), R.drawable.ic_favourite))
        } else {
            pokemonDetailsFavouriteImageView.setImageDrawable(ContextCompat.getDrawable( requireContext(), R.drawable.ic_not_favourite))
        }
    }

    private fun doFavouritePokemon() {
        mPokemon.favourite = !mPokemon.favourite
        mViewModel.doFavouritePokemon(mPokemon)
    }
}
