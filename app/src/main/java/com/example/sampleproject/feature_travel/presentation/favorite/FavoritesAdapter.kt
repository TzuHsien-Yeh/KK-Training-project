package com.example.sampleproject.feature_travel.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.core.ext.loadImage
import com.example.sampleproject.databinding.ItemFavoriteBinding
import com.example.sampleproject.feature_travel.domain.model.Attraction

class FavoritesAdapter(
    private val favoriteUiState: FavoriteUiState
    ): ListAdapter<Attraction, FavoritesAdapter.AttractionViewHolder>(DiffCallBack) {
    class AttractionViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(attraction: Attraction, favoriteUiState: FavoriteUiState){
            binding.imgAttraction.loadImage(attraction.images.firstOrNull())
            binding.textAttractionName.text = attraction.name
            binding.textAddress.text = attraction.address
            binding.textAttractionIntro.text = attraction.introduction

            val src = if (attraction.isFavorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off
            binding.btnFavorite.setImageResource(src)
            binding.btnFavorite.setOnClickListener {
                favoriteUiState.onFavoriteBtnClick(attraction)
            }
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
        return AttractionViewHolder(ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            favoriteUiState.onAttractionClick(item)
        }
        holder.bind(item, favoriteUiState)
    }

}