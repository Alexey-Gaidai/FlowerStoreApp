package com.example.flowerstoreapp.ui.catalog.bouquets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowerstoreapp.databinding.ItemBouquetBinding
import com.example.flowerstoreapp.databinding.ItemFlowerBinding
import com.example.flowerstoreapp.domain.models.Bouquets
import com.example.flowerstoreapp.domain.models.CartItem
import com.example.flowerstoreapp.domain.models.Flower
import com.example.flowerstoreapp.utils.ShoppingCart

class BouquetsAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<Bouquets, BouquetsAdapter.ElementsViewHolder>(
        NewsDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementsViewHolder {
        val binding = ItemBouquetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElementsViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ElementsViewHolder, position: Int) {
        val bouquet = getItem(position)
        holder.bind(bouquet)
    }

    inner class ElementsViewHolder(
        private val binding: ItemBouquetBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bouquets: Bouquets) {
            Glide
                .with(binding.root)
                .load(bouquets.imageUrl)
                .into(binding.ivImage)
            binding.tvTitle.text = bouquets.name
            binding.tvPrice.text = bouquets.price.toString()
            binding.btToCart.setOnClickListener{
                ShoppingCart.addItem(CartItem(bouquets, 0), itemView.context)
            }
            binding.root.setOnClickListener {
                onClick(bouquets.id)
            }
        }
    }

    private class NewsDiffCallback :
        DiffUtil.ItemCallback<Bouquets>() {
        override fun areItemsTheSame(
            oldItem: Bouquets,
            newItem: Bouquets
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Bouquets,
            newItem: Bouquets
        ): Boolean {
            return oldItem == newItem
        }
    }
}