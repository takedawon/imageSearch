package com.lanic.brandi.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.lanic.brandi.R
import com.lanic.brandi.base.BaseFragment
import com.lanic.brandi.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(
    layoutId = R.layout.fragment_search
) {

    private val viewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSearchImage("brandi", "1", "30")

        viewModel.searchImage.observe(viewLifecycleOwner, Observer {
            Timber.tag("test").e(it.toString())
        })
    }
}