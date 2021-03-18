package com.lanic.image.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.lanic.image.R
import com.lanic.image.base.BaseFragment
import com.lanic.image.databinding.FragmentSearchBinding
import com.lanic.image.util.EventObserver
import com.lanic.image.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(
    layoutId = R.layout.fragment_search
) {

    private val viewModel by viewModels<SearchViewModel>()
    private val imageAdapter by lazy { SearchImageAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        binding.rycSearch.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = imageAdapter
        }

        viewModel.searchImage.observe(viewLifecycleOwner, Observer { item ->
            imageAdapter.submitList(item)
        })

        viewModel.searchText.observe(viewLifecycleOwner, Observer { searchText ->
            viewModel.publishSubject.onNext(searchText)
        })

        viewModel.error.observe(viewLifecycleOwner, EventObserver { resource ->
            requireContext().toast(getString(resource))
        })

        viewModel.clear.observe(viewLifecycleOwner, EventObserver {
            imageAdapter.submitList(null)
        })
    }

    companion object {
        const val LOAD_DATA_SIZE = "30"
    }
}
