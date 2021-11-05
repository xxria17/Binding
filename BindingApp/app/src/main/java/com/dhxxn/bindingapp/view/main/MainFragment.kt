package com.dhxxn.bindingapp.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.databinding.FragmentMainBinding
import com.dhxxn.bindingapp.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment :  BaseFragment<FragmentMainBinding>(R.layout.fragment_main)  {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {

    }
}