package com.example.sampleproject.feature_travel.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.core.ext.loadImage
import com.example.sampleproject.databinding.ItemAttractionBinding
import com.example.sampleproject.feature_travel.domain.model.Attraction

class AttractionAdapter(
    private val listUiState: ListUiState
    ): ListAdapter<Attraction, AttractionAdapter.AttractionViewHolder>(DiffCallBack) {
    class AttractionViewHolder(private val binding: ItemAttractionBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(attraction: Attraction){
            binding.imgAttraction.loadImage(attraction.image)
            binding.textAttractionName.text = attraction.name
            binding.textAddress.text = attraction.address
            binding.textAttractionIntro.text = attraction.introduction
        }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Attraction>() {
        override fun areItemsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Attraction, newItem: Attraction): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        return AttractionViewHolder(ItemAttractionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            listUiState.onAttractionClick(item)
        }
        holder.bind(item)
    }

}