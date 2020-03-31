package com.pokeapp.ui.fragments.details.base_stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pokeapp.R

/**
 * A simple [Fragment] subclass.
 */
class BaseStatsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(id: String?) = BaseStatsFragment().apply {
            arguments = Bundle().apply {
                putString("id", id)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_stats, container, false)
    }

}
