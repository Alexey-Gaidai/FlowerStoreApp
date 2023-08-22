package com.example.flowerstoreapp.ui.currentBouquet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.flowerstoreapp.R
import com.example.flowerstoreapp.databinding.FragmentBouquetsBinding
import com.example.flowerstoreapp.databinding.FragmentCatalogBinding
import com.example.flowerstoreapp.databinding.FragmentCurrentBouquetBinding
import com.example.flowerstoreapp.domain.models.CartItem
import com.example.flowerstoreapp.domain.models.SingleBouquet
import com.example.flowerstoreapp.ui.catalog.bouquets.BouquetsViewModel
import com.example.flowerstoreapp.utils.ShoppingCart
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentBouquetFragment : Fragment() {

    private var _binding: FragmentCurrentBouquetBinding? = null
    private val binding get() = _binding!!
    private lateinit var bouquet: SingleBouquet
    private val model: CurrentBouquetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.loadBouquet(CurrentBouquetFragmentArgs.fromBundle(requireArguments()).bouquetId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentBouquetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        binding.btToCart.setOnClickListener {
            addToCart()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        model.bouquet.observe(viewLifecycleOwner) {
            bouquet = it
            initView()
        }
    }

    private fun addToCart() {
        val itemToCart = CartItem( bouquet, 0)
        ShoppingCart.addItem(itemToCart, requireContext())
    }

    private fun initView() {
        Glide
            .with(binding.root)
            .load(bouquet.imageUrl)
            .into(binding.ivBouquetImage)
        binding.tvBouquetTitle.text = bouquet.name
        binding.tvDescriptionValue.text = bouquet.description
        binding.tvCompositionValue.text =
            bouquet.bouquetComposition.joinToString(", ") { (quantity, flower) ->
                "$flower: $quantity"
            }
        binding.tvPriceValue.text = bouquet.price.toString()
    }
}