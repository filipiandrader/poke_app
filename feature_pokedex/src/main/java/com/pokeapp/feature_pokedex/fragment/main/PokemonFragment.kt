package com.pokeapp.feature_pokedex.fragment.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.bottomsheets.setPeekHeight
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.customview.BottomSheetGenerationViewHolder
import com.pokeapp.base_feature.customview.BottomSheetTypeViewHolder
import com.pokeapp.base_feature.util.delegateproperties.navDirections
import com.pokeapp.base_feature.util.enums.GenerationEnum
import com.pokeapp.base_feature.util.enums.PokemonTypeEnum
import com.pokeapp.base_feature.util.extensions.*
import com.pokeapp.base_presentation.model.GenerationBinding
import com.pokeapp.base_presentation.model.PokemonBinding
import com.pokeapp.base_presentation.model.TypeBinding
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
        binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupView() {
        activity?.window?.statusBarColor = requireContext().convertColor(R.color.background)
        viewModel.getAllPokemon(mOffset, mPrevious)
        viewModel.getTypes()
        viewModel.getGenerations()
        createCustomAnimation()

        binding.run {
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
            if (it.isEmpty() && pokemon.isEmpty()) {
                setupEmptyList()
            } else {
                setupPokedex(it)
            }
        }

        viewModel.fetchTypeViewState.onPostValue(owner) {
            type = it.toMutableList()
        }

        viewModel.fetchPokedexByTypeTypeViewState.onPostValue(owner) {
            pokemon.clear()
            setupPokedex(it)
        }

        viewModel.fetchGenerationViewState.onPostValue(owner) {
            generation = it.toMutableList()
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

    private fun createCustomAnimation() {
        val set = AnimatorSet()
        val scaleOutX = ObjectAnimator.ofFloat(pokemonMenuFAM.menuIconView, "scaleX", 1.0f, 0.2f)
        val scaleOutY = ObjectAnimator.ofFloat(pokemonMenuFAM.menuIconView, "scaleY", 1.0f, 0.2f)
        val scaleInX = ObjectAnimator.ofFloat(pokemonMenuFAM.menuIconView, "scaleX", 0.2f, 1.0f)
        val scaleInY = ObjectAnimator.ofFloat(pokemonMenuFAM.menuIconView, "scaleY", 0.2f, 1.0f)

        scaleOutX.duration = 50
        scaleOutY.duration = 50
        scaleInX.duration = 150
        scaleInY.duration = 150

        scaleInX.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                val img = when (pokemonMenuFAM.isOpened) {
                    true -> ContextCompat.getDrawable(requireContext(), R.drawable.ic_pokeball)
                    false -> ContextCompat.getDrawable(requireContext(), R.drawable.ic_close)
                }
                pokemonMenuFAM.menuIconView.setImageDrawable(img)
            }
        })

        set.play(scaleOutX).with(scaleOutY)
        set.play(scaleInX).with(scaleInY).after(scaleOutX)
        set.interpolator = OvershootInterpolator(2f)

        pokemonMenuFAM.iconToggleAnimatorSet = set
    }

    private fun showBottomSheetGeneration() {
        binding.run {
            pokemonMenuFAM.close(true)
            val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            val peekHeight = size.y * 0.70

            val dialog =
                MaterialDialog(requireActivity(), BottomSheet(LayoutMode.WRAP_CONTENT)).show {
                    setPeekHeight(literal = peekHeight.toInt())
                    customView(
                        viewRes = R.layout.bottom_sheet_layout,
                        scrollable = false,
                        noVerticalPadding = true,
                        horizontalPadding = false,
                        dialogWrapContent = true
                    )
                }

            val itemFilterNameTextView =
                dialog.getCustomView().findViewById<TextView>(R.id.itemFilterNameTextView)
            itemFilterNameTextView.putText(resources.getString(R.string.bottom_sheet_generation_label))

            val bottomSheetRecyclerView =
                dialog.getCustomView().findViewById<RecyclerView>(R.id.bottomSheetRecyclerView)

            bottomSheetRecyclerView.setup {
                withLayoutManager(GridLayoutManager(requireContext(), 2))
                withDataSource(dataSourceOf(generation))
                withItem<GenerationBinding, BottomSheetGenerationViewHolder>(R.layout.item_generation) {
                    onBind(::BottomSheetGenerationViewHolder) { _, item ->
                        val name = requireContext().getString(GenerationEnum.getName(item.name))
                        this.itemGenerationNameTextView.putText(name)
                        val icon = GenerationEnum.getIcon(item.name)
                        this.itemGenerationPhotoImageView.setImageResource(icon)
                    }

                    onClick { index ->
                        dialog.dismiss()
                        pokemon.clear()
                        hasPagination = false
                        viewModel.getPokemonByGenenration(generation[index].id)
                    }
                }
            }
        }
    }

    private fun showBottomSheetType() {
        binding.run {
            pokemonMenuFAM.close(true)
            val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            val peekHeight = size.y * 0.70

            val dialog =
                MaterialDialog(requireActivity(), BottomSheet(LayoutMode.WRAP_CONTENT)).show {
                    setPeekHeight(literal = peekHeight.toInt())
                    customView(
                        viewRes = R.layout.bottom_sheet_layout,
                        scrollable = false,
                        noVerticalPadding = true,
                        horizontalPadding = false,
                        dialogWrapContent = true
                    )
                }

            val itemFilterNameTextView =
                dialog.getCustomView().findViewById<TextView>(R.id.itemFilterNameTextView)
            itemFilterNameTextView.putText(resources.getString(R.string.bottom_sheet_type_label))

            val bottomSheetRecyclerView =
                dialog.getCustomView().findViewById<RecyclerView>(R.id.bottomSheetRecyclerView)

            bottomSheetRecyclerView.setup {
                withLayoutManager(GridLayoutManager(requireContext(), 2))
                withDataSource(dataSourceOf(type))
                withItem<TypeBinding, BottomSheetTypeViewHolder>(R.layout.item_type) {
                    onBind(::BottomSheetTypeViewHolder) { _, item ->
                        this.itemTypeNameTextView.putText(PokemonTypeEnum.match(item.name))
                        val color = requireContext().getPokemonColor(item.name)
                        this.itemTypeCardView.setColorFilter(color)
                    }

                    onClick { index ->
                        dialog.dismiss()
                        hasPagination = false
                        viewModel.getPokemonByType(type[index].name)
                    }
                }
            }
        }
    }
}
