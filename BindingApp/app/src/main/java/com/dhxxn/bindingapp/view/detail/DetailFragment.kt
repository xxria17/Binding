package com.dhxxn.bindingapp.view.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.databinding.FragmentDetailBinding
import com.dhxxn.bindingapp.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {

    }

}