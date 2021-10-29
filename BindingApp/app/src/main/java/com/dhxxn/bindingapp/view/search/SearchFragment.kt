package com.dhxxn.bindingapp.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.databinding.FragmentSearchBinding
import com.dhxxn.bindingapp.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search)  {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {

    }

}