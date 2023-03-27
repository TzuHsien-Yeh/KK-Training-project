package com.example.sampleproject.feature_travel.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.sampleproject.core.ext.loadImage
import com.example.sampleproject.databinding.FragmentDetailBinding
import com.example.sampleproject.feature_travel.domain.model.Attraction
import dagger.hilt.android.AndroidEntryPoint

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

        return binding.root
    }

    private fun showAttractionInfo(attraction: Attraction) {
        binding.imgAttraction.loadImage(attraction.image)
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

}