package com.lanic.brandi.ui

import com.lanic.brandi.R
import com.lanic.brandi.base.BaseActivity
import com.lanic.brandi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    layoutId = R.layout.activity_main
)