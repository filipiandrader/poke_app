package com.dexapp.feature_favoridex.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dexapp.base_feature.core.BaseFragment
import com.dexapp.base_feature.customview.bottomsheet.generation.GenerationBottomSheet
import com.dexapp.base_feature.customview.bottomsheet.type.TypeBottomSheet
import com.dexapp.base_feature.util.delegateproperties.navDirections
import com.dexapp.base_feature.util.extensions.*
import com.dexapp.base_presentation.model.generation.GenerationBinding
import com.dexapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.dexapp.base_presentation.model.type.TypeBinding
import com.dexapp.feature_favoridex.R
import com.dexapp.feature_favoridex.adapter.FavoridexAdapter
import com.dexapp.feature_favoridex.databinding.FragmentFavoridexBinding
import com.dexapp.feature_favoridex.navigation.main.FavoridexNavigation
import com.dexapp.presentation_favoridex.FavoridexViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoridexFragment : BaseFragment() {

    private val viewModel: FavoridexViewModel by viewModel()
    private val navigation: FavoridexNavigation by navDirections()

    private lateinit var binding: FragmentFavoridexBinding
    private lateinit var favoridexAdapter: FavoridexAdapter

    private var generation = mutableListOf<GenerationBinding>()
    private var type = mutableListOf<TypeBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        changeStatusBarColor(getColor())
        binding = FragmentFavoridexBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getColor() = requireContext().convertColor(R.color.background)

    override fun setupView() {
        viewModel.getFavoridex()

        val pokeballDrawable = getDrawableRes(R.drawable.ic_pokeball)
        val closeDrawable = getDrawableRes(R.drawable.ic_close)
        binding.favoridexMenuFAM.createCustomAnimation(pokeballDrawable, closeDrawable)
        binding.favoridexAllFAB.setOnClickListener {
            binding.favoridexMenuFAM.close(true)
            viewModel.getFavoridex()
        }
        binding.favoridexByGenFAB.setOnClickListener { showBottomSheetGeneration() }
        binding.favoridexByTypeFAB.setOnClickListener { showBottomSheetType() }
        binding.navigationIconImageView.setOnClickListener { navigation.navigateToHome() }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.fetchFavoridexViewState.onPostValue(owner) {
            if (it.favoridex.isEmpty()) {
                binding.favoridexMenuFAM.setGone()
                emptyList()
            } else {
                type = it.type.toMutableList()
                generation = it.generation.toMutableList()
                setupRecyclerView(it.favoridex)
            }
        }

        viewModel.fetchFavoridexByTypeViewState.onPostValue(owner) {
            if (it.isEmpty()) {
                emptyList()
            } else {
                setupRecyclerView(it)
            }
        }

        viewModel.fetchFavoridexByGenerationViewState.onPostValue(owner) {
            if (it.isEmpty()) {
                emptyList()
            } else {
                setupRecyclerView(it)
            }
        }
    }

    private fun emptyList() {
        binding.favoridexPlaceholderEmptyList.setVisible()
        binding.favoridexRecyclerView.setGone()
    }

    private fun showBottomSheetGeneration() {
        binding.favoridexMenuFAM.close(true)
        GenerationBottomSheet(requireParentFragment(), generation).show {
            viewModel.getPokemonByGenenration(it.region)
        }
    }

    private fun showBottomSheetType() {
        binding.favoridexMenuFAM.close(true)
        TypeBottomSheet(requireParentFragment(), type).show {
            viewModel.getPokemonByType(it.name)
        }
    }

    private fun setupRecyclerView(pokemon: List<PokemonInfoBinding>) {
        val layoutManager = if (pokemon.size == 1) {
            LinearLayoutManager(requireContext())
        } else {
            GridLayoutManager(requireContext(), 2)
        }
        favoridexAdapter = FavoridexAdapter { navigation.navigateToPokemonInfo(it) }
        favoridexAdapter.items = pokemon.toMutableList()
        binding.favoridexRecyclerView.apply {
            this.layoutManager = layoutManager
            adapter = favoridexAdapter
            setVisible()
        }
        binding.favoridexMenuFAM.setVisible()
        binding.favoridexRecyclerView.setVisible()
        binding.favoridexPlaceholderEmptyList.setGone()
        binding.favoridexRecyclerView.enableScroll(pokemon.size > 6)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        type.clear()
        generation.clear()
        viewModel.cleanValeus()
    }
}
