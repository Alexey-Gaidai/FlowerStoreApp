package com.example.flowerstoreapp.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowerstoreapp.databinding.ItemCartBinding
import com.example.flowerstoreapp.domain.models.CartItem
import com.example.flowerstoreapp.utils.ShoppingCart

class CartAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<CartItem, CartAdapter.ElementsViewHolder>(
        NewsDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementsViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElementsViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ElementsViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem)
    }

    inner class ElementsViewHolder(
        private val binding: ItemCartBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem) {
            Glide
                .with(binding.root)
                .load(cartItem.product.imageUrl)
                .into(binding.ivImage)
            binding.tvTitle.text = cartItem.product.name
            binding.tvPrice.text = cartItem.product.price.toString()
            binding.tvQuantity.text = cartItem.quantity.toString() + " шт."
            binding.btDelete.setOnClickListener{
                ShoppingCart.removeItem(cartItem, itemView.context)
            }
            binding.root.setOnClickListener {
                onClick(cartItem.product.id)
            }
        }
    }

    private class NewsDiffCallback :
        DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(
            oldItem: CartItem,
            newItem: CartItem
        ): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(
            oldItem: CartItem,
            newItem: CartItem
        ): Boolean {
            return oldItem == newItem
        }
    }
}