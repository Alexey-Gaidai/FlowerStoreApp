package com.example.flowerstoreapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowerstoreapp.databinding.FragmentProfileBinding
import com.example.flowerstoreapp.ui.catalog.bouquets.BouquetsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val model: ProfileViewModel by viewModels()
    private val adapter = ProfileOrdersAdapter {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
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
        model.orders.observe(viewLifecycleOwner) { bouquets ->
            adapter.submitList(bouquets)
        }
    }

    private fun initRecyclerView() {
        binding.rvOrders.adapter = adapter
        binding.rvOrders.layoutManager = LinearLayoutManager(context)
    }

}