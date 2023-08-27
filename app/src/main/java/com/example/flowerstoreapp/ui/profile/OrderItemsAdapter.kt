package com.example.flowerstoreapp.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowerstoreapp.databinding.ItemItemsInOrderBinding
import com.example.flowerstoreapp.databinding.ItemOrdersBinding
import com.example.flowerstoreapp.domain.models.Bouquets
import com.example.flowerstoreapp.domain.models.BouquetsInOrder
import com.example.flowerstoreapp.domain.models.Orders

class OrderItemsAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<BouquetsInOrder, OrderItemsAdapter.ElementsViewHolder>(
        NewsDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementsViewHolder {
        val binding = ItemItemsInOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElementsViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ElementsViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem)
    }

    inner class ElementsViewHolder(
        private val binding: ItemItemsInOrderBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bouquets: BouquetsInOrder) {
            binding.tvQuantity.text = bouquets.quantity.toString() + " шт."
            binding.tvTitle.text = bouquets.name
            Glide
                .with(binding.root)
                .load(bouquets.imageUrl)
                .into(binding.ivImage)
            binding.root.setOnClickListener {
                onClick(bouquets.id)
            }
        }
    }

    private class NewsDiffCallback :
        DiffUtil.ItemCallback<BouquetsInOrder>() {
        override fun areItemsTheSame(
            oldItem: BouquetsInOrder,
            newItem: BouquetsInOrder
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BouquetsInOrder,
            newItem: BouquetsInOrder
        ): Boolean {
            return oldItem == newItem
        }
    }
}