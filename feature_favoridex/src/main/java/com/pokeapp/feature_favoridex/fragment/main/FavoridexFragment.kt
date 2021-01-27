package com.pokeapp.feature_favoridex.fragment.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pokeapp.base_feature.core.BaseFragment
import com.pokeapp.base_feature.util.extensions.convertColor
import com.pokeapp.base_presentation.model.generation.GenerationBinding
import com.pokeapp.base_presentation.model.pokemon.PokemonBinding
import com.pokeapp.base_presentation.model.type.TypeBinding
import com.pokeapp.feature_favoridex.R
import com.pokeapp.presentation_favoridex.FavoridexViewModel
import kotlinx.android.synthetic.main.fragment_favoridex.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoridexFragment : BaseFragment() {

    private val viewModel: FavoridexViewModel by viewModel()

    private lateinit var generation: List<GenerationBinding>
    private lateinit var type: List<TypeBinding>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favoridex, container, false)

    override fun onStart() {
        super.onStart()

        // BACK BUTTON
        navigationIconImageView.setOnClickListener { findNavController().navigateUp() }

        viewModel.getFavouritePokemon()
        viewModel.getTypes()

        createCustomAnimation()

        favouriteAllFAB.setOnClickListener {
            favouriteMenuFAM.close(true)
            viewModel.getFavouritePokemon()
        }

        favouriteByGenFAB.setOnClickListener { showBottomSheetGeneration() }

        favouriteByTypeFAB.setOnClickListener { showBottomSheetType() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = requireContext().convertColor(R.color.background)
    }

    private fun createCustomAnimation() {
        val set = AnimatorSet()
        val scaleOutX = ObjectAnimator.ofFloat(favouriteMenuFAM.menuIconView, "scaleX", 1.0f, 0.2f)
        val scaleOutY = ObjectAnimator.ofFloat(favouriteMenuFAM.menuIconView, "scaleY", 1.0f, 0.2f)
        val scaleInX = ObjectAnimator.ofFloat(favouriteMenuFAM.menuIconView, "scaleX", 0.2f, 1.0f)
        val scaleInY = ObjectAnimator.ofFloat(favouriteMenuFAM.menuIconView, "scaleY", 0.2f, 1.0f)

        scaleOutX.duration = 50
        scaleOutY.duration = 50
        scaleInX.duration = 150
        scaleInY.duration = 150

        scaleInX.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                val img =
                    if (favouriteMenuFAM.isOpened) ContextCompat.getDrawable(
                        requireContext(), R.drawable.ic_pokeball
                    ) else ContextCompat.getDrawable(requireContext(), R.drawable.ic_close)
                favouriteMenuFAM.menuIconView.setImageDrawable(img)
            }
        })

        set.play(scaleOutX).with(scaleOutY)
        set.play(scaleInX).with(scaleInY).after(scaleOutX)
        set.interpolator = OvershootInterpolator(2f)

        favouriteMenuFAM.iconToggleAnimatorSet = set
    }

    private fun showBottomSheetGeneration() {
        /*favouriteMenuFAM.close(true)
        val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        val peekHeight = size.y * 0.70

        val dialog = MaterialDialog(requireActivity(), BottomSheet(LayoutMode.WRAP_CONTENT)).show {
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
        val generations = mutableListOf<GenerationBinding>()
        generations.add(GenerationBinding(id = 1, name = "1° Geração"))
        generations.add(GenerationBinding(id = 2, name = "2° Geração"))
        generations.add(GenerationBinding(id = 3, name = "3° Geração"))
        generations.add(GenerationBinding(id = 4, name = "4° Geração"))
        generations.add(GenerationBinding(id = 5, name = "5° Geração"))
        generations.add(GenerationBinding(id = 6, name = "6° Geração"))
        generations.add(GenerationBinding(id = 7, name = "7° Geração"))

        bottomSheetRecyclerView.setup {
            withLayoutManager(GridLayoutManager(requireContext(), 2))
            withDataSource(dataSourceOf(generations))
            withItem<GenerationBinding, BottomSheetGenerationViewHolder>(R.layout.item_generation) {
                onBind(::BottomSheetGenerationViewHolder) { _, item ->
                    this.itemGenerationNameTextView.putText(item.name)
//                    this.itemGenerationPhotoImageView.setImageResource(item.img)
                }

                onClick { index ->
                    dialog.dismiss()
                    viewModel.getPokemonByGenenration(generations[index].id.getGenerationName())
                }
            }
        }*/
    }

    private fun showBottomSheetType() {
        /*   favouriteMenuFAM.close(true)
           val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
           val display = wm.defaultDisplay
           val size = Point()
           display.getSize(size)
           val peekHeight = size.y * 0.70

           val dialog = MaterialDialog(requireActivity(), BottomSheet(LayoutMode.WRAP_CONTENT)).show {
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
                       this.itemTypeNameTextView.putText(item.name)

                       val color = itemView.context.getPokemonColor(item.name)
                       this.itemTypeCardView.background.colorFilter =
                           PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                   }

                   onClick { index ->
                       dialog.dismiss()
                       viewModel.getPokemonByType(type[index].name)
                   }
               }
           }*/
    }

    private fun setupRecyclerView(pokemon: MutableList<PokemonBinding>) {
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
}
