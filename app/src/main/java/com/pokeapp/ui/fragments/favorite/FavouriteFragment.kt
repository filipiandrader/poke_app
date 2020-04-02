package com.pokeapp.ui.fragments.favourite

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.R
import com.pokeapp.databinding.FragmentFavouriteBinding
import com.pokeapp.presentation.State
import com.pokeapp.presentation.favourite.FavouriteViewModel
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.ui.fragments.PokemonViewHolder
import com.pokeapp.util.PokemonColorUtil
import com.pokeapp.util.putText
import com.pokeapp.util.setVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_favourite.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class FavouriteFragment : Fragment() {

    private val mViewModel: FavouriteViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val bindind: FragmentFavouriteBinding = FragmentFavouriteBinding.inflate(inflater, container, false)
        bindind.viewModel = mViewModel
        creatingObservers()
        bindind.executePendingBindings()
        return bindind.root
    }

    override fun onStart() {
        super.onStart()

        // BACK BUTTON
        navigationIconImageView.setOnClickListener { findNavController().navigateUp() }

        mViewModel.getFavouritePokemon()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = PokemonColorUtil(view.context).convertColor(R.color.background)
    }

    private fun creatingObservers() {
        mViewModel.getState().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.LOADING -> {
                    favouriteProgressBar.setVisible(true)
                    favouriteRecyclerView.setVisible(false)
                }
                State.SUCCESS -> {
                    favouriteProgressBar.setVisible(false)
                    mViewModel.getState().value?.data?.let { pokemon ->
                        setupRecyclerView(pokemon)
                    }
                }
                State.FAILURE -> {
                    viewState.throwable?.message?.let {
                        favouriteMessageTextView.setVisible(true)
                        favouriteRecyclerView.setVisible(false)
                        favouriteProgressBar.setVisible(false)
                    }
                }
                else -> { /* ignore */
                }
            }
        })
    }

    private fun setupRecyclerView(pokemon: MutableList<Pokemon>) {
        if (pokemon.isNotEmpty()) {
            val layoutManager = if (pokemon.size == 1) {
                LinearLayoutManager(context)
            } else {
                GridLayoutManager(context, 2)
            }
            favouriteRecyclerView.setup {
                withLayoutManager(layoutManager)
                withDataSource(dataSourceOf(pokemon))
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
                        findNavController().navigate(R.id.action_favouriteFragment_to_pokemonDetailsFragment, bundle)
                    }
                }
            }
            favouriteMessageTextView.setVisible(false)

            favouriteRecyclerView.isNestedScrollingEnabled = pokemon.size > 6
        } else {
            favouriteMessageTextView.setVisible(true)
            favouriteMessageTextView.putText("Sem pokemons favoritados :(")
        }

        favouriteRecyclerView.setVisible(pokemon.isNotEmpty())
    }

}
