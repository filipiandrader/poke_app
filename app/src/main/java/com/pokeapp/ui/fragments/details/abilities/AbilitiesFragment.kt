package com.pokeapp.ui.fragments.details.abilities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.pokeapp.R
import com.pokeapp.presentation.model.Ability
import com.pokeapp.presentation.model.Pokemon
import com.pokeapp.util.formatNameAbility
import com.pokeapp.util.putText
import kotlinx.android.synthetic.main.fragment_abilities.*

/**
 * A simple [Fragment] subclass.
 */
class AbilitiesFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(pokemon: Pokemon) = AbilitiesFragment().apply {
            arguments = Bundle().apply {
                putSerializable("pokemon", pokemon)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_abilities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemon = checkNotNull(arguments?.getSerializable("pokemon") as Pokemon)
        abilitiesRecyclerView.setup {
            withLayoutManager(GridLayoutManager(view.context, 2))
            withDataSource(dataSourceOf(pokemon.abilities))
            withItem<Ability, AbilityViewHolder>(R.layout.item_ability) {
                onBind(::AbilityViewHolder) { _, item ->
                    this.itemAbilityTextView.putText(item.name.formatNameAbility())
                }
            }
        }
    }

}
