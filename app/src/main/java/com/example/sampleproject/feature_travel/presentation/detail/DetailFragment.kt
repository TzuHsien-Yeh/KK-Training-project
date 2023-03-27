package com.example.sampleproject.feature_travel.presentation.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.fragment.navArgs
import com.example.sampleproject.R
import com.example.sampleproject.core.ext.loadImage
import com.example.sampleproject.databinding.FragmentDetailBinding
import com.example.sampleproject.databinding.FragmentListBinding
import com.example.sampleproject.feature_travel.domain.model.Attraction
import com.example.sampleproject.feature_travel.presentation.list.ListViewModel
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
        showAttractionInfo(attraction)
        viewModel.favoriteAttractions.observe(viewLifecycleOwner) {
            Timber.d("viewModel.favoriteAttractions.observe: $it")
        }

        return binding.root
    }

    private fun showAttractionInfo(attraction: Attraction){
        binding.imgAttraction.loadImage(attraction.image)
        binding.textAttractionName.text = attraction.name
        binding.textAddress.text = attraction.address
        binding.textAttractionIntro.text = attraction.introduction
        binding.textAttractionOpenTime.text = attraction.openTime
    }
}