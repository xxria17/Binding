package com.dhxxn.bindingapp.view.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.databinding.FragmentFavoriteBinding
import com.dhxxn.bindingapp.view.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {

    }

}