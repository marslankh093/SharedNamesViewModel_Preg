package com.ne.samplenewars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ne.samplenewars.databinding.FragmentListBinding

class FavFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: SharedNamesViewModel by activityViewModels()

    private val adapter = FavAdapter { fav ->
        viewModel.deleteFromFav(fav.id)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.favNames.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loadFavNames()
        return binding.root
    }
}
