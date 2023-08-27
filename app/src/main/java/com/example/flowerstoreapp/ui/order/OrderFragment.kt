package com.example.flowerstoreapp.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flowerstoreapp.R
import com.example.flowerstoreapp.databinding.FragmentOrderBinding
import com.example.flowerstoreapp.utils.ShoppingCart
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private val model: OrderViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        binding.btOrder.setOnClickListener {
            if (binding.etCity.text.isBlank() || binding.etStreet.text.isBlank() || binding.etHouse.text.isBlank() || binding.etApartments.text.isBlank()) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                createOrder()
            }
        }
        binding.tvTotalValue.text = ShoppingCart.getCartTotal()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        model.isOk.observe(viewLifecycleOwner) { unit ->
            unit.getContentIfNotHandled()?.let {
                navigateToScreen()
            }
        }
    }

    private fun navigateToScreen() {
        findNavController().navigate(R.id.action_orderFragment_to_orderedFragment)
    }

    private fun createOrder() {
        val city = binding.etCity.text.toString()
        val street = binding.etStreet.text.toString()
        val house = binding.etHouse.text.toString()
        val apartments = binding.etApartments.text.toString()

        model.createOrder(city, street, house, apartments)
    }
}