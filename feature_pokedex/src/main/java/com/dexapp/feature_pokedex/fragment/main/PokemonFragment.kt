package com.dexapp.feature_pokedex.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dexapp.base_feature.core.BaseFragment
import com.dexapp.base_feature.customview.bottomsheet.generation.GenerationBottomSheet
import com.dexapp.base_feature.customview.bottomsheet.type.TypeBottomSheet
import com.dexapp.base_feature.util.delegateproperties.navDirections
import com.dexapp.base_feature.util.extensions.*
import com.dexapp.base_presentation.model.generation.GenerationBinding
import com.dexapp.base_presentation.model.pokemon.PokemonBinding
import com.dexapp.base_presentation.model.type.TypeBinding
import com.dexapp.feature_pokedex.R
import com.dexapp.feature_pokedex.adapter.PokedexAdapter
import com.dexapp.feature_pokedex.databinding.FragmentPokemonBinding
import com.dexapp.feature_pokedex.navigation.PokedexNavigation
import com.dexapp.presentation_pokedex.pokemon.PokemonViewModel
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

        val pokeballDrawable = getDrawableRes(R.drawable.ic_pokeball)
        val closeDrawable = getDrawableRes(R.drawable.ic_close)
        binding.pokemonMenuFAM.createCustomAnimation(pokeballDrawable, closeDrawable)
        binding.pokemonAllFAB.setOnClickListener { getAllPokedex() }
        binding.pokemonByGenFAB.setOnClickListener { showBottomSheetGeneration() }
        binding.pokemonByTypeFAB.setOnClickListener { showBottomSheetType() }
        binding.navigationIconImageView.setOnClickListener { navigation.navigateToHome() }

        binding.pokemonRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (hasPagination) {
                    visibleItemCount = binding.pokemonRecyclerView.childCount()
                    totalItemCount = binding.pokemonRecyclerView.itemCount()
                    firstVisibleItemPosition =
                        binding.pokemonRecyclerView.findFirstVisibleItemPosition()

                    if (!isLoading && (firstVisibleItemPosition + visibleItemCount) >= totalItemCount) {
                        mPrevious = mOffset
                        mOffset += 20
                        isLoading = true
                        binding.pokemonPaginationProgressBar.setVisible(true)
                        viewModel.getAllPokemon(mOffset, mPrevious)
                    }
                }
            }
        })
    }

    private fun getAllPokedex() {
        hasPagination = true
        binding.pokemonMenuFAM.close(true)
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
        binding.pokemonPlaceholderEmptyList.setVisible()
        binding.pokemonPaginationProgressBar.setGone()
    }

    private fun setupPokedex(pokedex: List<PokemonBinding>) {
        binding.pokemonPlaceholderEmptyList.setGone()
        pokemon.addAll(pokemon.size, pokedex)
        pokedexAdapter = PokedexAdapter { navigation.navigateToPokemonInfo(it) }
        pokedexAdapter.items = pokemon.toMutableList()
        binding.pokemonRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = pokedexAdapter
            setVisible()
        }

        if (isLoading) {
            isLoading = false
            binding.pokemonRecyclerView.scrollToPosition(mPrevious - 1)
            binding.pokemonPaginationProgressBar.setGone()
        }
        binding.pokemonMenuFAM.setVisible()
    }

    private fun showBottomSheetGeneration() {
        binding.pokemonMenuFAM.close(true)
        GenerationBottomSheet(requireParentFragment(), generation).show {
            pokemon.clear()
            hasPagination = false
            viewModel.getPokemonByGenenration(it.id)
        }
    }

    private fun showBottomSheetType() {
        binding.pokemonMenuFAM.close(true)
        TypeBottomSheet(requireParentFragment(), type).show {
            pokemon.clear()
            hasPagination = false
            viewModel.getPokemonByType(it.name)
        }
    }

    override fun onStop() {
        super.onStop()
        hasPagination = true
        pokemon.clear()
        type.clear()
        generation.clear()
        mOffset = 20
        mPrevious = 0
        viewModel.cleanValues()
    }
}
