package com.example.sampleproject.feature_travel.presentation.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.core.ext.loadImage
import com.example.sampleproject.core.ext.toUri
import com.example.sampleproject.databinding.ItemImageBinding
import com.example.sampleproject.databinding.ItemVideoBinding
import com.example.sampleproject.feature_travel.presentation.model.AttractionMedia

class MediaAdapter(private val player: Player) :
    ListAdapter<AttractionMedia, RecyclerView.ViewHolder>(DiffCallBack) {

    class VideoViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("UnsafeOptInUsageError")
        fun bind(video: Int, player: Player) {
            player.setMediaItem(MediaItem.fromUri(video.toUri()))
            player.prepare()

            binding.videoView.apply {
                this.player = player
                useController = true
                setShowShuffleButton(false)
                setShowFastForwardButton(false)
                setShowRewindButton(false)
                setShowPreviousButton(false)
                setShowNextButton(false)
            }
        }
    }

    class ImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) {
            binding.imgAttraction.loadImage(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_VIDEO -> VideoViewHolder(
                ItemVideoBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            ITEM_VIEW_TYPE_IMAGE -> ImageViewHolder(
                ItemImageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> throw java.lang.ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VideoViewHolder -> {
                holder.bind(
                    (getItem(position) as AttractionMedia.Video).video,
                    player
                )
            }
            is ImageViewHolder -> {
                holder.bind(
                    (getItem(position) as AttractionMedia.Image).image
                )
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AttractionMedia.Video -> ITEM_VIEW_TYPE_VIDEO
            is AttractionMedia.Image -> ITEM_VIEW_TYPE_IMAGE
        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<AttractionMedia>() {
        override fun areItemsTheSame(oldItem: AttractionMedia, newItem: AttractionMedia): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: AttractionMedia,
            newItem: AttractionMedia
        ): Boolean {
            return oldItem == newItem
        }

        private const val ITEM_VIEW_TYPE_VIDEO = 0x00
        private const val ITEM_VIEW_TYPE_IMAGE = 0x01
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        player.pause()
    }
}