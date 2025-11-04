package com.ne.samplenewars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ne.samplenewars.databinding.FragmentListBinding

class SuffFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: SharedNamesViewModel by activityViewModels()

    private val adapter = SuffAdapter { name ->
        viewModel.addToFav(name.name)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.suffNames.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.addStaticSuffNames()
        viewModel.loadSuffNames()

        return binding.root
    }
}
