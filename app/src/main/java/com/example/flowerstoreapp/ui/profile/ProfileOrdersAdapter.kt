package com.example.flowerstoreapp.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowerstoreapp.databinding.ItemOrdersBinding
import com.example.flowerstoreapp.domain.models.Orders

class ProfileOrdersAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<Orders, ProfileOrdersAdapter.ElementsViewHolder>(
        NewsDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementsViewHolder {
        val binding = ItemOrdersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElementsViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ElementsViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem)
    }

    inner class ElementsViewHolder(
        private val binding: ItemOrdersBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Orders) {
            binding.tvNumber.text = "Заказ номер: ${order.id}"
            binding.tvAddress.text = order.address
            binding.tvDate.text = order.orderDate
            binding.tvTotal.text = String.format("%.2f", order.bouquets.sumOf { it.price })
            val adapter = OrderItemsAdapter(onClick)
            adapter.submitList(order.bouquets)
            binding.rvItemsInOrder.adapter = adapter
            binding.rvItemsInOrder.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private class NewsDiffCallback :
        DiffUtil.ItemCallback<Orders>() {
        override fun areItemsTheSame(
            oldItem: Orders,
            newItem: Orders
        ): Boolean {
            return oldItem.orderDate == newItem.orderDate
        }

        override fun areContentsTheSame(
            oldItem: Orders,
            newItem: Orders
        ): Boolean {
            return oldItem == newItem
        }
    }
}