package com.pokeapp.ui.fragments.favorite

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
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.R
import com.pokeapp.databinding.FragmentFavoriteBinding
import com.pokeapp.presentation.State
import com.pokeapp.presentation.favorite.FavoriteViewModel
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.ui.fragments.PokemonViewHolder
import com.pokeapp.util.PokemonColorUtil
import com.pokeapp.util.putText
import com.pokeapp.util.setVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private val mViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val bindind: FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        bindind.viewModel = mViewModel
        creatingObservers()
        bindind.executePendingBindings()
        return bindind.root
    }

    override fun onResume() {
        super.onResume()

        mViewModel.getFavoritePokemon()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = PokemonColorUtil(view.context).convertColor(R.color.background)
    }

    private fun creatingObservers() {
        mViewModel.getState().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.LOADING -> favoriteProgressBar.setVisible(true)
                State.SUCCESS -> {
                    favoriteProgressBar.setVisible(false)
                    mViewModel.getState().value?.data?.let { pokemon ->
                        setupRecyclerView(pokemon)
                    }
                }
                State.FAILURE -> {
                    viewState.throwable?.message?.let {
                        favoriteMessageTextView.setVisible(true)
                        favoriteRecyclerView.setVisible(false)
                        favoriteProgressBar.setVisible(false)
                    }
                }
                else -> { /* ignore */
                }
            }
        })
    }

    private fun setupRecyclerView(pokemon: MutableList<Pokemon>) {
        if (pokemon.isNotEmpty()) {
            favoriteRecyclerView.setup {
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
                        this.itemPokemonConstraintLayout.background.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

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
                        findNavController().navigate(R.id.action_pokemonFragment_to_pokemonDetailsFragment, bundle)
                    }
                }
            }
            favoriteMessageTextView.setVisible(false)
            favoriteRecyclerView.setVisible(true)
        } else {
            favoriteMessageTextView.setVisible(true)
            favoriteMessageTextView.putText("Sem pokemons favoritados :(")
        }
    }

}
