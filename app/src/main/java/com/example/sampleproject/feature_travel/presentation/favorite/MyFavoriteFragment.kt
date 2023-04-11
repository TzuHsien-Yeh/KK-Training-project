package com.example.sampleproject.feature_travel.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sampleproject.NavGraphDirections
import com.example.sampleproject.databinding.FragmentMyFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFavoriteFragment : Fragment() {

    private val viewModel: MyFavoriteViewModel by viewModels()
    private var _binding: FragmentMyFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyFavoriteBinding.inflate(layoutInflater)

        setUpFavoritesRecyclerView()
        setUpNavigation()

        return binding.root
    }

    private fun setUpFavoritesRecyclerView() {
        val adapter = FavoritesAdapter(viewModel.favoriteUiState)
        binding.recyclerViewFavoriteAttractions.adapter = adapter
        viewModel.favoriteAttractions.observe(viewLifecycleOwner){
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpNavigation() {
        viewModel.navigateToDetail.observe(viewLifecycleOwner){
            it?.let {
                findNavController().navigate(NavGraphDirections.actionGlobalDetailFragment(it))
                viewModel.doneNavigating()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}