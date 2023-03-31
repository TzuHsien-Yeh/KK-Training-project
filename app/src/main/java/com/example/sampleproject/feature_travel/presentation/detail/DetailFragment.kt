package com.example.sampleproject.feature_travel.presentation.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.sampleproject.core.ext.loadImage
import com.example.sampleproject.databinding.FragmentDetailBinding
import com.example.sampleproject.feature_travel.domain.model.Attraction
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.net.URL


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)

        val args: DetailFragmentArgs by navArgs()
        val attraction = args.attractionKey
        showAttractionInfo(attraction)
        setFavoriteBtn()

        setUpVideoPlayer()
        Timber.d("onCreateView attraction = $attraction")

        // Set up toolbar with area name
        (activity as AppCompatActivity).supportActionBar?.title = attraction.name

        return binding.root
    }

    private fun showAttractionInfo(attraction: Attraction) {
//        binding.imgAttraction.loadImage(attraction.image)
        binding.textAttractionName.text = attraction.name
        binding.textAddress.text = attraction.address
        binding.textAttractionIntro.text = attraction.introduction
        binding.textAttractionOpenTime.text = attraction.openTime
    }

    private fun setFavoriteBtn() {
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            val starStatus =
                if (isFavorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off

            binding.btnFavorite.setImageResource(starStatus)

            binding.btnFavorite.setOnClickListener {
                viewModel.toggleFavoriteState(isFavorite)
            }
        }
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun setUpVideoPlayer() {
        binding.videoView.apply {
            player = viewModel.player
            useController = true
            setShowShuffleButton(false)
            setShowFastForwardButton(false)
            setShowRewindButton(false)
            setShowPreviousButton(false)
            setShowNextButton(false)
        }
        viewModel.initPlayer()

//        Glide.with(this)
//            .asBitmap()
//            .load(viewModel.attraction?.image)
//            .into(object : CustomTarget<Bitmap>(){
//                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                    binding.videoView.defaultArtwork = (BitmapDrawable(resource))
//                }
//                override fun onLoadCleared(placeholder: Drawable?) {
//                }
//            })

//        viewModel.shouldShowThumbnail.observe(viewLifecycleOwner) {
//            if (it) {
//                Glide.with(this)
//                    .asBitmap()
//                    .load(viewModel.attraction?.image)
//                    .into(object : CustomTarget<Bitmap>(){
//                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                            binding.playerControlView.setBackgroundDrawable(BitmapDrawable(resource))
//                        }
//                        override fun onLoadCleared(placeholder: Drawable?) {
//                        }
//                    })
//            } else {
//                binding.playerControlView.setBackgroundDrawable(null)
//            }
//        }

    }

}
