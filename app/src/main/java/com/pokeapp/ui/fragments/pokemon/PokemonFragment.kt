package com.pokeapp.ui.fragments.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
                State.SUCCESS -> {
                    pokemonMessageTextView.setVisible(false)
                    pokemonProgressBar.setVisible(false)
                    mViewModel.getState().value?.data?.let { pokemon ->
                        setupRecyclerView(pokemon)
                    }
                }
                State.LOADING -> pokemonProgressBar.setVisible(true)
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
        mPreviousSize = mPokemon.size
        mPokemon.addAll(mPokemon.size, pokemon)
        if (mPokemon.isNotEmpty()) {
            pokemonRecyclerView.setup {
                withDataSource(dataSourceOf(mPokemon))
                withItem<Pokemon, PokemonViewHolder>(R.layout.item_pokemon) {
                    onBind(::PokemonViewHolder) { index, item ->
                        Picasso.get().load(item.photo).into(this.itemPokemonImageView)
                        val name = "#${index + 1} - ${item.name}"
                        this.itemPokemonTextView.putText(name)
                    }
                    onClick { index ->
                        val bundle = bundleOf("pokemon" to pokemon[index])
                        findNavController().navigate(R.id.action_pokemonFragment_to_pokemonDetailsFragment, bundle)
                    }
                }
            }
            pokemonRecyclerView.setVisible(true)

            if (isLoading) {
                isLoading = false
                pokemonRecyclerView.scrollToPosition(mPreviousSize - 1)
                pokemonPaginationProgressBar.setVisible(false)
            }
        } else {
            pokemonMessageTextView.setVisible(true)
            pokemonMessageTextView.putText("Sem pokemons cadastrados :(")
        }
    }
}
