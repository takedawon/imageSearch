package com.lanic.image.ui

import com.lanic.image.R
import com.lanic.image.base.BaseActivity
import com.lanic.image.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    layoutId = R.layout.activity_main
)
