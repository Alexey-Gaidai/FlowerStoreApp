package com.example.flowerstoreapp.ui.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flowerstoreapp.databinding.ItemFlowerBinding
import com.example.flowerstoreapp.domain.models.Flower

class FlowersAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<Flower, FlowersAdapter.ElementsViewHolder>(
        NewsDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementsViewHolder {
        val binding = ItemFlowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElementsViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ElementsViewHolder, position: Int) {
        val stockInfo = getItem(position)
        holder.bind(stockInfo)
    }

    inner class ElementsViewHolder(
        private val binding: ItemFlowerBinding,
        private val onClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(flower: Flower) {
            binding.tvFlowerName.text = flower.name
            binding.root.setOnClickListener {
                onClick(flower.id)
            }
        }
    }

    private class NewsDiffCallback :
        DiffUtil.ItemCallback<Flower>() {
        override fun areItemsTheSame(
            oldItem: Flower,
            newItem: Flower
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Flower,
            newItem: Flower
        ): Boolean {
            return oldItem == newItem
        }
    }
}