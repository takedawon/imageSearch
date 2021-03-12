package com.lanic.brandi.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.lanic.brandi.R
import com.lanic.brandi.base.BaseFragment
import com.lanic.brandi.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(
    layoutId = R.layout.fragment_search
) {

    val viewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSearchImage("brandi", "1", "30")

    }
}