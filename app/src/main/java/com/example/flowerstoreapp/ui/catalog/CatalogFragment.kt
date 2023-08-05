package com.example.flowerstoreapp.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowerstoreapp.databinding.FragmentCatalogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!

    private val adapter = FlowersAdapter {
        navigateToBouquetsByFlower(it)
    }
    private val model: CatalogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
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
        model.flowers.observe(viewLifecycleOwner) { flowers ->
            adapter.submitList(flowers)
        }
    }

    private fun initRecyclerView() {
        binding.rvFlowers.adapter = adapter
        binding.rvFlowers.layoutManager = LinearLayoutManager(context)
    }

    private fun navigateToBouquetsByFlower(id: Int) {
        val action = CatalogFragmentDirections.actionNavigationCatalogToBouquetsFragment(id)
        findNavController().navigate(action)
        Toast.makeText(context, "Потом переход на букеты из конкретных цветов id: $id", Toast.LENGTH_SHORT).show()
    }
}