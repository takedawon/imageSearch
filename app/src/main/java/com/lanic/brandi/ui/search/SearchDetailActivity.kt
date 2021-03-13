package com.lanic.brandi.ui.search

import android.os.Bundle
import androidx.navigation.NavArgs
import androidx.navigation.navArgs
import com.lanic.brandi.R
import com.lanic.brandi.base.BaseActivity
import com.lanic.brandi.databinding.ActivitySearchDetailBinding

class SearchDetailActivity : BaseActivity<ActivitySearchDetailBinding>(
    layoutId = R.layout.activity_search_detail
) {

    private val argument by navArgs<SearchDetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.item = argument.item
    }
}