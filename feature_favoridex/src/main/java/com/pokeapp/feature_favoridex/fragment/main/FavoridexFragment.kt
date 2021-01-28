package com.pokeapp.feature_favoridex.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.customview.bottomsheet.generation.GenerationBottomSheet
import com.pokeapp.base_feature.customview.bottomsheet.type.TypeBottomSheet
import com.pokeapp.base_feature.util.delegateproperties.navDirections
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.generation.GenerationBinding
import com.pokeapp.base_presentation.model.pokemon.PokemonInfoBinding
import com.pokeapp.base_presentation.model.type.TypeBinding
import com.pokeapp.feature_favoridex.R
import com.pokeapp.feature_favoridex.databinding.FragmentFavoridexBinding
import com.pokeapp.feature_favoridex.navigation.main.FavoridexNavigation
import com.pokeapp.presentation_favoridex.FavoridexViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoridexFragment : BaseFragment() {

    private val viewModel: FavoridexViewModel by viewModel()
    private val navigation: FavoridexNavigation by navDirections()

    private lateinit var binding: FragmentFavoridexBinding

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

        binding.apply {
            navigationIconImageView.setOnClickListener { navigation.navigateToHome() }

            val pokeballDrawable = getDrawableRes(R.drawable.ic_pokeball)
            val closeDrawable = getDrawableRes(R.drawable.ic_close)
            favouriteMenuFAM.createCustomAnimation(pokeballDrawable, closeDrawable)
            favouriteAllFAB.setOnClickListener {
                favouriteMenuFAM.close(true)
                viewModel.getFavoridex()
            }

            favouriteByGenFAB.setOnClickListener { showBottomSheetGeneration() }

            favouriteByTypeFAB.setOnClickListener { showBottomSheetType() }
        }
    }

    override fun addObservers(owner: LifecycleOwner) {
        viewModel.fetchFavoridexViewState.onPostValue(owner) {
            if (it.favoridex.isEmpty()) {
                emptyList()
            } else {
                type = it.type.toMutableList()
                generation = it.generation.toMutableList()
                binding.favouriteMenuFAM.setVisible()
                binding.favouriteRecyclerView.setVisible()
            }
        }
    }

    private fun emptyList() {
        binding.favouriteMenuFAM.setGone()
        binding.favouriteRecyclerView.setGone()
    }

    private fun showBottomSheetGeneration() {
        binding.apply {
            favouriteMenuFAM.close(true)
            GenerationBottomSheet(requireParentFragment(), generation).show {
                viewModel.getPokemonByGenenration(it.region)
            }
        }
    }

    private fun showBottomSheetType() {
        binding.apply {
            favouriteMenuFAM.close(true)
            TypeBottomSheet(requireParentFragment(), type).show {
                viewModel.getPokemonByType(it.name)
            }
        }
    }

    private fun setupRecyclerView(pokemon: List<PokemonInfoBinding>) {
        /*if (pokemon.isNotEmpty()) {
            val layoutManager = if (pokemon.size == 1) {
                LinearLayoutManager(context)
            } else {
                GridLayoutManager(context, 2)
            }
            favouriteRecyclerView.setup {
                withLayoutManager(layoutManager)
                withDataSource(dataSourceOf(pokemon))
                withItem<PokemonBinding, PokemonViewHolder>(R.layout.item_pokemon) {
                    onBind(::PokemonViewHolder) { _, item ->
                        Picasso.get().load(item.photo).placeholder(android.R.color.transparent)
                            .into(this.itemPokemonPhotoImageView)
                        this.itemPokemonNameTextView.putText(item.name)
                        val id = when (item.id) {
                            in 1..9 -> {
                                "#00${item.id}"
                            }
                            in 10..99 -> {
                                "#0${item.id}"
                            }
                            else -> {
                                "#${item.id}"
                            }
                        }
                        this.itemPokemonIDTextView.putText(id)

                        val color = itemView.context.getPokemonColor(item.types)
                        this.itemPokemonCardView.background.colorFilter =
                            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

                        if (item.types.size == 1) {
                            if (item.types[0].name == "dark") {
                                this.itemPokemonIDTextView.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.colorIcons
                                    )
                                )
                            }
                        } else if (item.types.size == 2) {
                            if (item.types[1].name == "dark") {
                                this.itemPokemonIDTextView.setTextColor(
                                    ContextCompat.getColor(
                                        requireContext(),
                                        R.color.colorIcons
                                    )
                                )
                            }
                        }

                        item.types.getOrNull(0).let { firstType ->
                            this.itemPokemonType1TextView.putText(firstType?.name ?: "")
                            this.itemPokemonType1TextView.setVisible(firstType != null)
                        }

                        item.types.getOrNull(1).let { secondType ->
                            this.itemPokemonType2TextView.putText(secondType?.name ?: "")
                            this.itemPokemonType2TextView.setVisible(secondType != null)
                        }

                        item.types.getOrNull(2).let { thirdType ->
                            this.itemPokemonType3TextView.putText(thirdType?.name ?: "")
                            this.itemPokemonType3TextView.setVisible(thirdType != null)
                        }
                    }

                    onClick { index ->
                        val bundle = bundleOf("pokemon" to pokemon[index])
                        findNavController().navigate(
                            R.id.action_favouriteFragment_to_pokemonDetailsFragment,
                            bundle
                        )
                    }
                }
            }
            favouriteMessageTextView.setVisible(false)

            favouriteRecyclerView.isNestedScrollingEnabled = pokemon.size > 6
        } else {
            favouriteMessageTextView.setVisible(true)
            favouriteMessageTextView.putText("Sem pokemons favoritados :(")
        }

        favouriteProgressBar.setVisible(false)
        favouriteRecyclerView.setVisible(pokemon.isNotEmpty())
        favouriteMenuFAM.setVisible(true)*/
    }

    override fun onStop() {
        super.onStop()
        type.clear()
        generation.clear()
        viewModel.cleanValeus()
    }
}
