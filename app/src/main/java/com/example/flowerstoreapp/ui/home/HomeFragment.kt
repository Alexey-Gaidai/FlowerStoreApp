package com.example.flowerstoreapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flowerstoreapp.databinding.FragmentHomeBinding
import com.example.flowerstoreapp.ui.catalog.bouquets.BouquetsAdapter
import com.example.flowerstoreapp.ui.catalog.bouquets.BouquetsFragmentDirections
import com.example.flowerstoreapp.ui.catalog.bouquets.BouquetsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapterTop = BouquetsAdapter {
        navigateToSingleBouquet(it)
    }
    private val adapterTopWithRoses = BouquetsAdapter {
        navigateToSingleBouquet(it)
    }
    private val model: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
        model.topSelling.observe(viewLifecycleOwner) { bouquets ->
            adapterTop.submitList(bouquets)
        }
        model.topSellingWithRoses.observe(viewLifecycleOwner) { bouquets ->
            adapterTopWithRoses.submitList(bouquets)
        }
    }

    private fun initRecyclerView() {
        binding.rvHits.adapter = adapterTop
        binding.rvHits.layoutManager = GridLayoutManager(context, 2)
        binding.rvBestRose.adapter = adapterTopWithRoses
        binding.rvBestRose.layoutManager = GridLayoutManager(context, 2)
    }

    private fun navigateToSingleBouquet(bouquetId: Int) {
        val action = HomeFragmentDirections.actionNavigationHomeToNavigationCurrentBouquet(bouquetId)
        findNavController().navigate(action)
    }
}