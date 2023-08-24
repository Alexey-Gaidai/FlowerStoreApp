package com.example.flowerstoreapp.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowerstoreapp.databinding.FragmentCartBinding
import com.example.flowerstoreapp.ui.catalog.bouquets.BouquetsAdapter
import com.example.flowerstoreapp.ui.catalog.bouquets.BouquetsFragmentDirections
import com.example.flowerstoreapp.ui.catalog.bouquets.BouquetsViewModel

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val adapter = CartAdapter {
        navigateToSingleBouquet(it)
    }

    private val model: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
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
        lifecycleScope.launchWhenStarted {
            model.cart.collect { items ->
                adapter.submitList(items)
            }
        }
        model.price.observe(viewLifecycleOwner) {
            binding.tvTotalValue.text = it.toString().format("%.2f") + " ₽"
        }
    }

    private fun initRecyclerView() {
        binding.rvCart.adapter = adapter
        binding.rvCart.layoutManager = LinearLayoutManager(context)
    }

    private fun navigateToSingleBouquet(bouquetId: Int) {
        val action = CartFragmentDirections.actionNavigationCartToCurrentBouquetFragment(bouquetId)
        findNavController().navigate(action)
    }
}