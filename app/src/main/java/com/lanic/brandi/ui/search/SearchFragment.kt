package com.lanic.brandi.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.lanic.brandi.R
import com.lanic.brandi.base.BaseFragment
import com.lanic.brandi.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

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

        viewModel.searchImage.observe(viewLifecycleOwner, Observer { images ->
            imageAdapter.submitList(images)
        })

        viewModel.searchText.observe(viewLifecycleOwner, Observer { searchText ->
            imageAdapter.submitList(null)
            viewModel.publishSubject.onNext(searchText)
        })

        viewModel.isSearchResult.observe(viewLifecycleOwner, Observer { isResultExist ->
            if (isResultExist) {
                binding.apply {
                    rycSearch.visibility = View.VISIBLE
                    tvSearchResult.visibility = View.GONE
                }
            } else {
                binding.apply {
                    rycSearch.visibility = View.INVISIBLE
                    tvSearchResult.visibility = View.VISIBLE
                }
            }
        })

        viewModel.publishSubject.debounce(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { searchText ->
                if (!searchText.isNullOrBlank())
                    viewModel.getSearchImage(searchText, "1", "30")
            }
    }
}