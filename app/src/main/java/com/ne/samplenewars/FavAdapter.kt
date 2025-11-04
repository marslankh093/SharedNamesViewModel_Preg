package com.ne.samplenewars

import com.ne.samplenewars.SuffName.FavName
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ne.samplenewars.databinding.ItemNameBinding

class FavAdapter(private val onHeartClick: (FavName) -> Unit) :
    ListAdapter<FavName, FavAdapter.FavViewHolder>(DiffCallback()) {

    class FavViewHolder(val binding: ItemNameBinding) : RecyclerView.ViewHolder(binding.root)
    class DiffCallback : DiffUtil.ItemCallback<FavName>() {
        override fun areItemsTheSame(oldItem: FavName, newItem: FavName) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: FavName, newItem: FavName) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = ItemNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val fav = getItem(position)
        with(holder.binding) {
            nameText.text = fav.name
            heartIcon.setImageResource(R.drawable.ic_heart_filled)
            heartIcon.setOnClickListener { onHeartClick(fav) }
        }
    }
}
