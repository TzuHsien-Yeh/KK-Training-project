package com.example.sampleproject.feature_travel.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.sampleproject.databinding.FragmentDetailBinding
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.presentation.mapper.toAttractionMedia
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


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
        setUpCarouselView()
        showAttractionInfo(attraction)
        setFavoriteBtn()

        Timber.d("onCreateView attraction = $attraction")

        // Set up toolbar with area name
        (activity as AppCompatActivity).supportActionBar?.title = attraction.name

        return binding.root
    }

    private fun setUpCarouselView() {
        val adapter = MediaAdapter(viewModel.player)
        binding.recyclerViewAttractionMedia.adapter = adapter
        adapter.submitList(viewModel.attraction?.toAttractionMedia())

        LinearSnapHelper().attachToRecyclerView(binding.recyclerViewAttractionMedia)
    }

    private fun showAttractionInfo(attraction: Attraction) {
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
