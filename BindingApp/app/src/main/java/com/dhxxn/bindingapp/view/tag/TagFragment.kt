package com.dhxxn.bindingapp.view.tag

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.databinding.FragmentTagBinding
import com.dhxxn.bindingapp.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TagFragment : BaseFragment<FragmentTagBinding>(R.layout.fragment_tag) {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTagBinding {
        return FragmentTagBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {

    }

}