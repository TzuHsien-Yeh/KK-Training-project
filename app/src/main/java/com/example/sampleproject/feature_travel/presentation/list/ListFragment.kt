package com.example.sampleproject.feature_travel.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.NavGraphDirections
import com.example.sampleproject.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(layoutInflater)

        viewModel.getAttractions()
        setUpAttractionRecyclerView()
        loadMoreUponReachingBottom()
        handleError()
        navigateToAttractionDetail()

        return binding.root
    }

    private fun setUpAttractionRecyclerView() {
        val adapter = AttractionAdapter(viewModel.listUiState)
        binding.recyclerViewAttractionList.adapter = adapter
        viewModel.attractionList.observe(viewLifecycleOwner){
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
            Timber.d("viewModel.attractionList.observe: listSize = ${it?.size}")
        }
    }

    private fun loadMoreUponReachingBottom() {
        binding.recyclerViewAttractionList.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                // if can NOT (!) scroll vertically DOWN (direction: 1) anymore && at idle state
                // direction integers: -1 for up, 1 for down, 0 will always return false
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.loadMoreAttractions()
                }
            }
        })

        // Show loading msg or progress bar
        viewModel.isLoading.observe(viewLifecycleOwner){
            if (it) {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun handleError() {
        viewModel.errorMsg.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToAttractionDetail() {
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
