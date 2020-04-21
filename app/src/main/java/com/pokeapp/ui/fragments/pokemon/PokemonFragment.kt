package com.pokeapp.ui.fragments.pokemon

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Point
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
import com.pokeapp.R
import com.pokeapp.databinding.FragmentPokemonBinding
import com.pokeapp.presentation.State
import com.pokeapp.presentation.model.Generation
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.model.Type
import com.pokeapp.presentation.pokemon.PokemonViewModel
import com.pokeapp.ui.fragments.BottomSheetGenerationViewHolder
import com.pokeapp.ui.fragments.BottomSheetTypeViewHolder
import com.pokeapp.ui.fragments.PokemonViewHolder
import com.pokeapp.util.PokemonColorUtil
import com.pokeapp.util.putText
import com.pokeapp.util.setTypeName
import com.pokeapp.util.setVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon.*
import org.jetbrains.anko.textColor
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 */
class PokemonFragment : Fragment() {

    private val mViewModel: PokemonViewModel by viewModel()

    private lateinit var mPokemon: MutableList<Pokemon>
    private lateinit var mTypes: MutableList<Type>

    private var mOffset = 0

    var isLoading = false

    var visibleItemCount = 0
    var totalItemCount = 0
    var firstVisibleItemPosition = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentPokemonBinding = FragmentPokemonBinding.inflate(inflater, container, false)

        mPokemon = mutableListOf()

        binding.viewModel = mViewModel
        creatingObservers()
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = PokemonColorUtil(view.context).convertColor(R.color.background)
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
                val img = if (pokemonMenuFAM.isOpened) context!!.getDrawable(R.drawable.ic_pokeball) else context!!.getDrawable(R.drawable.ic_close)
                pokemonMenuFAM.menuIconView.setImageDrawable(img)
            }
        })

        set.play(scaleOutX).with(scaleOutY)
        set.play(scaleInX).with(scaleInY).after(scaleOutX)
        set.interpolator = OvershootInterpolator(2f)

        pokemonMenuFAM.iconToggleAnimatorSet = set
    }

    private fun showBottomSheetGeneration() {
        pokemonMenuFAM.close(true)
        val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val peekHeight = size.y * 0.70

        val dialog = MaterialDialog(activity!!, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            setPeekHeight(literal = peekHeight.toInt())
            customView(viewRes = R.layout.bottom_sheet_layout, scrollable = false, noVerticalPadding = true, horizontalPadding = false, dialogWrapContent = true)
        }

        val itemFilterNameTextView = dialog.getCustomView().findViewById<TextView>(R.id.itemFilterNameTextView)
        itemFilterNameTextView.putText(resources.getString(R.string.bottom_sheet_generation_label))

        val bottomSheetRecyclerView = dialog.getCustomView().findViewById<RecyclerView>(R.id.bottomSheetRecyclerView)
        val generations = mutableListOf<Generation>()
        generations.add(Generation(id = 1, name = "1° Geração", img = R.drawable.gen1))
        generations.add(Generation(id = 2, name = "2° Geração", img = R.drawable.gen2))
        generations.add(Generation(id = 3, name = "3° Geração", img = R.drawable.gen3))
        generations.add(Generation(id = 4, name = "4° Geração", img = R.drawable.gen4))
        generations.add(Generation(id = 5, name = "5° Geração", img = R.drawable.gen5))
        generations.add(Generation(id = 6, name = "6° Geração", img = R.drawable.gen6))
        generations.add(Generation(id = 7, name = "7° Geração", img = R.drawable.gen7))

        bottomSheetRecyclerView.setup {
            withLayoutManager(GridLayoutManager(context!!, 2))
            withDataSource(dataSourceOf(generations))
            withItem<Generation, BottomSheetGenerationViewHolder>(R.layout.item_generation) {
                onBind(::BottomSheetGenerationViewHolder) { _, item ->
                    this.itemGenerationNameTextView.putText(item.name)
                    this.itemGenerationPhotoImageView.setImageResource(item.img)
                }

                onClick { index ->
                    dialog.dismiss()
                    mViewModel.getPokemonByGenenration(generations[index].id)
                }
            }
        }
    }

    private fun showBottomSheetType() {
        pokemonMenuFAM.close(true)
        val wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val peekHeight = size.y * 0.70

        val dialog = MaterialDialog(activity!!, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            setPeekHeight(literal = peekHeight.toInt())
            customView(viewRes = R.layout.bottom_sheet_layout, scrollable = false, noVerticalPadding = true, horizontalPadding = false, dialogWrapContent = true)
        }

        val itemFilterNameTextView = dialog.getCustomView().findViewById<TextView>(R.id.itemFilterNameTextView)
        itemFilterNameTextView.putText(resources.getString(R.string.bottom_sheet_type_label))

        val bottomSheetRecyclerView = dialog.getCustomView().findViewById<RecyclerView>(R.id.bottomSheetRecyclerView)

        bottomSheetRecyclerView.setup {
            withLayoutManager(GridLayoutManager(context!!, 2))
            withDataSource(dataSourceOf(mTypes))
            withItem<Type, BottomSheetTypeViewHolder>(R.layout.item_type) {
                onBind(::BottomSheetTypeViewHolder) { _, item ->
                    this.itemTypeNameTextView.putText(setTypeName(item.name))

                    val color = PokemonColorUtil(itemView.context).getPokemonColor(item.name)
                    this.itemTypeCardView.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                }

                onClick { index ->
                    dialog.dismiss()
                    mViewModel.getPokemonByType(mTypes[index].id)
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()

        mViewModel.getAllPokemon(mOffset)
        mViewModel.getTypes()

        createCustomAnimation()

        pokemonAllFAB.setOnClickListener {
            mPokemon.clear()
            mOffset = 0
            mViewModel.getAllPokemon(mOffset)
        }

        pokemonByGenFAB.setOnClickListener { showBottomSheetGeneration() }

        pokemonByTypeFAB.setOnClickListener { showBottomSheetType() }

        // BACK BUTTON
        navigationIconImageView.setOnClickListener { findNavController().navigateUp() }

        // PAGINATION
        pokemonRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                visibleItemCount = pokemonRecyclerView.layoutManager!!.childCount
                totalItemCount = pokemonRecyclerView.layoutManager!!.itemCount
                firstVisibleItemPosition = (pokemonRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (!isLoading && (firstVisibleItemPosition + visibleItemCount) >= totalItemCount) {
                    mOffset += 20
                    isLoading = true
                    pokemonPaginationProgressBar.setVisible(true)
                    mViewModel.getAllPokemon(mOffset)
                }
            }
        })
    }

    private fun creatingObservers() {
        mViewModel.getState().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.LOADING -> {
                    pokemonProgressBar.setVisible(true)
                    pokemonRecyclerView.setVisible(false)
                    pokemonMessageTextView.setVisible(false)
                    pokemonMenuFAM.setVisible(false)
                }
                State.SUCCESS -> {
                    pokemonMessageTextView.setVisible(false)
                    pokemonProgressBar.setVisible(false)
                    mViewModel.getState().value?.data?.let { pokemon ->
                        setupRecyclerView(pokemon)
                    }
                }
                State.FAILURE -> {
                    pokemonMessageTextView.setVisible(true)
                    pokemonRecyclerView.setVisible(false)
                    pokemonProgressBar.setVisible(false)
                }
                else -> { /* ignore */
                }
            }
        })

        mViewModel.getStateByGeneration().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.LOADING -> {
                    mPokemon.clear()
                    mOffset = 0
                    pokemonProgressBar.setVisible(true)
                    pokemonRecyclerView.setVisible(false)
                    pokemonMessageTextView.setVisible(false)
                    pokemonMenuFAM.setVisible(false)
                }
                State.SUCCESS -> {
                    mViewModel.getStateByGeneration().value?.data?.let { pokemon ->
                        setupRecyclerView(pokemon)
                    }
                }
                State.FAILURE -> {
                    pokemonMessageTextView.setVisible(true)
                    pokemonRecyclerView.setVisible(false)
                    pokemonProgressBar.setVisible(false)
                }
                else -> { /* ignore */
                }
            }
        })

        mViewModel.getStateTypes().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.SUCCESS -> {
                    mTypes = mutableListOf()
                    mViewModel.getStateTypes().value?.data?.let {
                        mTypes.addAll(it)
                    }
                }
                else -> {
                    // IGNORE
                }
            }
        })

        mViewModel.getStateByType().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.LOADING -> {
                    mPokemon.clear()
                    mOffset = 0
                    pokemonProgressBar.setVisible(true)
                    pokemonRecyclerView.setVisible(false)
                    pokemonMenuFAM.setVisible(false)
                    pokemonMessageTextView.setVisible(false)
                }
                State.SUCCESS -> {
                    pokemonMessageTextView.setVisible(false)
                    pokemonProgressBar.setVisible(false)
                    mViewModel.getStateByType().value?.data?.let { pokemon ->
                        setupRecyclerView(pokemon)
                    }
                }
                State.FAILURE -> {
                    pokemonMessageTextView.setVisible(true)
                    pokemonRecyclerView.setVisible(false)
                    pokemonProgressBar.setVisible(false)
                }
                else -> { /* ignore */
                }
            }
        })
    }

    private fun setupRecyclerView(pokemon: MutableList<Pokemon>) {
        mPokemon.addAll(mPokemon.size, pokemon)
        if (mPokemon.isNotEmpty()) {
            pokemonRecyclerView.setup {
                withLayoutManager(GridLayoutManager(context, 2))
                withDataSource(dataSourceOf(mPokemon))
                withItem<Pokemon, PokemonViewHolder>(R.layout.item_pokemon) {
                    onBind(::PokemonViewHolder) { _, item ->
                        Picasso.get().load(item.photo).placeholder(android.R.color.transparent).into(this.itemPokemonPhotoImageView)
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

                        val color = PokemonColorUtil(itemView.context).getPokemonColor(item.types)
                        this.itemPokemonCardView.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

                        if (item.types.size == 1) {
                            if (item.types[0].name == "dark") {
                                this.itemPokemonIDTextView.textColor = ContextCompat.getColor(context!!, R.color.colorIcons)
                            }
                        } else if (item.types.size == 2) {
                            if (item.types[1].name == "dark") {
                                this.itemPokemonIDTextView.textColor = ContextCompat.getColor(context!!, R.color.colorIcons)
                            }
                        }

                        item.types.getOrNull(0).let { firstType ->
                            this.itemPokemonType1TextView.putText(setTypeName(firstType?.name))
                            this.itemPokemonType1TextView.setVisible(firstType != null)
                        }

                        item.types.getOrNull(1).let { secondType ->
                            this.itemPokemonType2TextView.putText(setTypeName(secondType?.name))
                            this.itemPokemonType2TextView.setVisible(secondType != null)
                        }

                        item.types.getOrNull(2).let { thirdType ->
                            this.itemPokemonType3TextView.putText(setTypeName(thirdType?.name))
                            this.itemPokemonType3TextView.setVisible(thirdType != null)
                        }
                    }

                    onClick { index ->
                        val bundle = bundleOf("pokemon" to mPokemon[index])
                        findNavController().navigate(R.id.action_pokemonFragment_to_pokemonDetailsFragment, bundle)
                    }
                }
            }

            if (isLoading) {
                isLoading = false
                pokemonRecyclerView.scrollToPosition(mOffset - 1)
                pokemonPaginationProgressBar.setVisible(false)
            }
        } else {
            pokemonMessageTextView.setVisible(true)
            pokemonMessageTextView.putText("Sem pokemons cadastrados :(")
        }
        pokemonRecyclerView.setVisible(mPokemon.isNotEmpty())
        pokemonProgressBar.setVisible(false)
        pokemonMenuFAM.setVisible(true)
    }
}
