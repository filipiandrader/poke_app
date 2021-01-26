package com.pokeapp.feature_pokedex.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.customview.bottomsheet.generation.GenerationBottomSheet
import com.pokeapp.base_feature.customview.bottomsheet.type.TypeBottomSheet
import com.pokeapp.base_feature.util.delegateproperties.navDirections
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.generation.GenerationBinding
import com.pokeapp.base_presentation.model.pokemon.PokemonBinding
import com.pokeapp.base_presentation.model.type.TypeBinding
import com.pokeapp.feature_pokedex.R
import com.pokeapp.feature_pokedex.adapter.PokedexAdapter
import com.pokeapp.feature_pokedex.databinding.FragmentPokemonBinding
import com.pokeapp.feature_pokedex.navigation.PokedexNavigation
import com.pokeapp.presentation_pokedex.pokemon.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_pokemon.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonFragment : BaseFragment() {

    private val viewModel: PokemonViewModel by viewModel()
    private val navigation: PokedexNavigation by navDirections()

    private val pokemon = mutableListOf<PokemonBinding>()
    private var type = mutableListOf<TypeBinding>()
    private var generation = mutableListOf<GenerationBinding>()

    private lateinit var binding: FragmentPokemonBinding
    private lateinit var pokedexAdapter: PokedexAdapter

    private var mOffset = 20
    private var mPrevious = 0

    var isLoading = false
    private var hasPagination = true

    var visibleItemCount = 0
    var totalItemCount = 0
    var firstVisibleItemPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        changeStatusBarColor(getColor())
        binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getColor() = requireContext().convertColor(R.color.background)

    override fun setupView() {
        viewModel.getAllPokemon(mOffset, mPrevious)

        binding.run {
            val pokeballDrawable = getDrawableRes(R.drawable.ic_pokeball)
            val closeDrawable = getDrawableRes(R.drawable.ic_close)
            pokemonMenuFAM.createCustomAnimation(pokeballDrawable, closeDrawable)
            pokemonAllFAB.setOnClickListener { getAllPokedex() }
            pokemonByGenFAB.setOnClickListener { showBottomSheetGeneration() }
            pokemonByTypeFAB.setOnClickListener { showBottomSheetType() }
            navigationIconImageView.setOnClickListener { navigation.navigateToHome() }

            pokemonRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (hasPagination) {
                        visibleItemCount = pokemonRecyclerView.layoutManager!!.childCount
                        totalItemCount = pokemonRecyclerView.layoutManager!!.itemCount
                        firstVisibleItemPosition =
                            (pokemonRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                        if (!isLoading && (firstVisibleItemPosition + visibleItemCount) >= totalItemCount) {
                            mPrevious = mOffset
                            mOffset += 20
                            isLoading = true
                            pokemonPaginationProgressBar.setVisible(true)
                            viewModel.getAllPokemon(mOffset, mPrevious)
                        }
                    }
                }
            })
        }
    }

    private fun getAllPokedex() {
        hasPagination = true
        pokemonMenuFAM.close(true)
        pokemon.clear()
        mOffset = 20
        mPrevious = 0
        viewModel.getAllPokemon(mOffset, mPrevious)
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.fetchPokedexViewState.onPostValue(owner) {
            type = it.type.toMutableList()
            generation = it.generation.toMutableList()
            if (it.pokedex.isEmpty() && pokemon.isEmpty()) {
                setupEmptyList()
            } else {
                setupPokedex(it.pokedex)
            }
        }

        viewModel.fetchPokedexByTypeTypeViewState.onPostValue(owner) {
            pokemon.clear()
            setupPokedex(it)
        }

        viewModel.fetchPokedexByGenerationTypeViewState.onPostValue(owner) {
            pokemon.clear()
            setupPokedex(it)
        }
    }

    private fun setupEmptyList() {
        pokemonPaginationProgressBar.setGone()
    }

    private fun setupPokedex(pokedex: List<PokemonBinding>) {
        pokemon.addAll(pokemon.size, pokedex)
        binding.run {
            pokedexAdapter = PokedexAdapter { navigation.navigateToPokemonInfo(it) }
            pokedexAdapter.items = pokemon.toMutableList()
            pokemonRecyclerView.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = pokedexAdapter
                setVisible()
            }

            if (isLoading) {
                isLoading = false
                pokemonRecyclerView.scrollToPosition(mPrevious - 1)
                pokemonPaginationProgressBar.setGone()
            }
            pokemonProgressBar.setGone()
            pokemonMenuFAM.setVisible()
        }
    }

    private fun showBottomSheetGeneration() {
        binding.run {
            pokemonMenuFAM.close(true)
            GenerationBottomSheet(requireParentFragment(), generation).show {
                pokemon.clear()
                hasPagination = false
                viewModel.getPokemonByGenenration(it.id)
            }
        }
    }

    private fun showBottomSheetType() {
        binding.run {
            pokemonMenuFAM.close(true)
            TypeBottomSheet(requireParentFragment(), type).show {
                pokemon.clear()
                hasPagination = false
                viewModel.getPokemonByType(it.name)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        pokemon.clear()
        generation.clear()
        type.clear()
        viewModel.cleanValues()
    }
}
