package com.lanic.image.ui.search

import android.os.Bundle
import androidx.navigation.navArgs
import com.lanic.image.R
import com.lanic.image.base.BaseActivity
import com.lanic.image.databinding.ActivitySearchDetailBinding

class SearchDetailActivity : BaseActivity<ActivitySearchDetailBinding>(
    layoutId = R.layout.activity_search_detail
) {

    private val argument by navArgs<SearchDetailActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.item = argument.item
    }
}