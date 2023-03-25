package com.example.sampleproject.feature_travel.presentation.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.sampleproject.R
import com.example.sampleproject.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(layoutInflater)

        viewModel.getAttractions()

        val adapter = AttractionAdapter()
        binding.recyclerViewAttractionList.adapter = adapter
        viewModel.attractionList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        viewModel.errorMsg.observe(viewLifecycleOwner){
            it?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

}