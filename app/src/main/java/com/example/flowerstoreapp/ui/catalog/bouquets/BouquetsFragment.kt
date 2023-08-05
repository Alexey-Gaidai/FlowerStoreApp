package com.example.flowerstoreapp.ui.catalog.bouquets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowerstoreapp.R
import com.example.flowerstoreapp.databinding.FragmentBouquetsBinding
import com.example.flowerstoreapp.databinding.FragmentCatalogBinding
import com.example.flowerstoreapp.ui.catalog.CatalogViewModel
import com.example.flowerstoreapp.ui.catalog.FlowersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BouquetsFragment : Fragment() {
    private var _binding: FragmentBouquetsBinding? = null
    private val binding get() = _binding!!
    private val adapter = BouquetsAdapter {
        navigateToSingleBouquet(it)
    }

    private val model: BouquetsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.loadBouquets(BouquetsFragmentArgs.fromBundle(requireArguments()).flowerId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBouquetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        model.bouquets.observe(viewLifecycleOwner) { bouquets ->
            adapter.submitList(bouquets)
        }
    }

    private fun initRecyclerView() {
        binding.rvBouquets.adapter = adapter
        binding.rvBouquets.layoutManager = GridLayoutManager(context, 2)
    }

    private fun navigateToSingleBouquet(bouquetId: Int) {
        val action = BouquetsFragmentDirections.actionBouquetsFragmentToCurrentBouquetFragment(bouquetId)
        findNavController().navigate(action)
    }
}