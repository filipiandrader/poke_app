package com.pokeapp.ui.fragments.pokemon

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem

import com.pokeapp.R
import com.pokeapp.databinding.FragmentPokemonBinding
import com.pokeapp.presentation.State
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.presentation.pokemon.PokemonViewModel
import com.pokeapp.ui.fragments.PokemonViewHolder
import com.pokeapp.util.PokemonColorUtil
import com.pokeapp.util.putText
import com.pokeapp.util.setVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class PokemonFragment : Fragment() {

    private val mViewModel: PokemonViewModel by viewModel()

    private lateinit var mPokemon: MutableList<Pokemon>

    private var mOffset = 0
    private var mPreviousSize = 0

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

    override fun onStart() {
        super.onStart()

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

    override fun onResume() {
        super.onResume()

        mViewModel.getAllPokemon(mOffset)
    }

    private fun creatingObservers() {
        mViewModel.getState().observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState?.state) {
                State.LOADING -> pokemonProgressBar.setVisible(true)
                State.SUCCESS -> {
                    pokemonMessageTextView.setVisible(false)
                    pokemonProgressBar.setVisible(false)
                    mViewModel.getState().value?.data?.let { pokemon ->
                        setupRecyclerView(pokemon)
                    }
                }
                State.FAILURE -> {
                    viewState.throwable?.message?.let {
                        pokemonMessageTextView.setVisible(true)
                        pokemonRecyclerView.setVisible(false)
                        pokemonProgressBar.setVisible(false)
                        pokemonMessageTextView.putText(it)
                    }
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
                        val bundle = bundleOf("pokemon" to mPokemon[index])
                        findNavController().navigate(R.id.action_pokemonFragment_to_pokemonDetailsFragment, bundle)
                    }
                }
            }
            pokemonRecyclerView.setVisible(true)

            if (isLoading) {
                isLoading = false
                pokemonRecyclerView.scrollToPosition(mOffset - 1)
                pokemonPaginationProgressBar.setVisible(false)
            }
        } else {
            pokemonMessageTextView.setVisible(true)
            pokemonMessageTextView.putText("Sem pokemons cadastrados :(")
        }
    }
}
