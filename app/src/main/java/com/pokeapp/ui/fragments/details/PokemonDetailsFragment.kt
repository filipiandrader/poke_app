package com.pokeapp.ui.fragments.details

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.pokeapp.R
import com.pokeapp.databinding.FragmentPokemonDetailsBinding
import com.pokeapp.presentation.State
import com.pokeapp.presentation.details.PokemonDetailsViewModel
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.ui.fragments.PokemonDetailsViewPagerAdapter
import com.pokeapp.util.PokemonColorUtil
import com.pokeapp.util.putText
import com.pokeapp.util.setRefresh
import com.pokeapp.util.setVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.support.v4.longToast
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class PokemonDetailsFragment : Fragment() {

    private val mViewModel: PokemonDetailsViewModel by viewModel()

    private lateinit var mPokemon: Pokemon

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPokemonDetailsBinding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        creatingObservers()
        binding.executePendingBindings()
        return binding.root
    }

    private fun creatingObservers() {
        mViewModel.getStateInfo().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.LOADING -> {
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
                State.FAILURE -> longToast("Ocorreu um erro ao favoritar/desfavoritar ${mPokemon.name} :(")
                else -> {
                    // IGNORE
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()

        // BACK BUTTON
        navigationIconImageView.setOnClickListener { findNavController().navigateUp() }
    }

    private fun bindInfo(pokemon: Pokemon) {
        mPokemon.height = pokemon.height
        mPokemon.weight = pokemon.weight
        mPokemon.base_experience = pokemon.base_experience
        mPokemon.abilities = pokemon.abilities
        mPokemon.moves = pokemon.moves
        mPokemon.stats = pokemon.stats
        mPokemon.evolves = pokemon.evolves

        pokemonDetailsViewPager.adapter = PokemonDetailsViewPagerAdapter(requireFragmentManager(), requireContext(), mPokemon)
        pokemonDetailsTabLayout.setupWithViewPager(pokemonDetailsViewPager)

        pokemonDetailsProgressBar.setVisible(false)
        pokemonDetailsViewPager.setVisible(true)
    }

    private fun showToast() {
        if (mPokemon.favourite) {
            longToast("${mPokemon.name} foi favoritado :)")
        } else {
            longToast("${mPokemon.name} foi desfavoritado :(")
        }

        setFavouriteIconCorrectly()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPokemon = checkNotNull(arguments?.getSerializable("pokemon") as Pokemon)
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

        val color = PokemonColorUtil(view.context).getPokemonColor(mPokemon.types)
        pokemonDetailsAppBarLayout.backgroundColor = color
        pokemonDetailsCollapsingToolbarLayout.contentScrim?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        activity?.window?.statusBarColor = color

        Picasso.get().load(mPokemon.photo).into(pokemonDetailsNormalImageView)
        Picasso.get().load(mPokemon.photo_shiny).into(pokemonDetailsShinyImageView)

        mPokemon.types.getOrNull(0).let { firstType ->
            pokemonDetailsType3TextView.putText(firstType?.name ?: "")
            pokemonDetailsType3TextView.setVisible(firstType != null)
        }

        mPokemon.types.getOrNull(1).let { secondType ->
            pokemonDetailsType2TextView.putText(secondType?.name ?: "")
            pokemonDetailsType2TextView.setVisible(secondType != null)
        }

        mPokemon.types.getOrNull(2).let { thirdType ->
            pokemonDetailsType1TextView.putText(thirdType?.name ?: "")
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
            pokemonDetailsFavouriteImageView.setImageDrawable(context!!.resources.getDrawable(R.drawable.ic_favourite))
        } else {
            pokemonDetailsFavouriteImageView.setImageDrawable(context!!.resources.getDrawable(R.drawable.ic_not_favourite))
        }
    }

    private fun doFavouritePokemon() {
        mPokemon.favourite = !mPokemon.favourite
        mViewModel.doFavouritePokemon(mPokemon)
    }
}
