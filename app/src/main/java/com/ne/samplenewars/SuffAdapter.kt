package com.ne.samplenewars

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ne.samplenewars.SuffName.SuffName
import com.ne.samplenewars.databinding.ItemNameBinding

class SuffAdapter(private val onHeartClick: (SuffName) -> Unit)
    : ListAdapter<SuffName, SuffAdapter.SuffViewHolder>(DiffCallback()) {

    class SuffViewHolder(val binding: ItemNameBinding) : RecyclerView.ViewHolder(binding.root)
    class DiffCallback : DiffUtil.ItemCallback<SuffName>() {
        override fun areItemsTheSame(oldItem: SuffName, newItem: SuffName) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: SuffName, newItem: SuffName) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuffViewHolder {
        val binding = ItemNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuffViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuffViewHolder, position: Int) {
        val name = getItem(position)
        with(holder.binding) {
            nameText.text = name.name
            heartIcon.setImageResource(R.drawable.ic_heart_outline)
            heartIcon.setOnClickListener { onHeartClick(name) }
        }
    }
}
