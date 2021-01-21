package com.pokeapp.feature_pokedex.fragment.info.abilities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.base_feature.util.extensions.formatNameAbility
import com.pokeapp.base_feature.util.extensions.putText
import com.pokeapp.base_presentation.model.AbilityBinding
import com.pokeapp.base_presentation.model.PokemonInfoBinding
import com.pokeapp.feature_pokedex.R
import kotlinx.android.synthetic.main.fragment_abilities.*

class AbilitiesFragment : Fragment() {

    private lateinit var pokemon: PokemonInfoBinding

    companion object {
        @JvmStatic
        fun newInstance(pokemon: PokemonInfoBinding) = AbilitiesFragment().apply {
            arguments = Bundle().apply {
                putParcelable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_abilities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemon = checkNotNull(arguments?.getParcelable("pokemon"))
        abilitiesRecyclerView.setup {
            withLayoutManager(GridLayoutManager(view.context, 2))
            withDataSource(dataSourceOf(pokemon.abilities))
            withItem<AbilityBinding, AbilityViewHolder>(R.layout.item_ability) {
                onBind(::AbilityViewHolder) { _, item ->
                    this.itemAbilityTextView.putText(item.name.formatNameAbility())
                }
            }
        }
    }
}
