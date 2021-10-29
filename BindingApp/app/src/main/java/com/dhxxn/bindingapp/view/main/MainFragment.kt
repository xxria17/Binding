package com.dhxxn.bindingapp.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.databinding.FragmentMainBinding
import com.dhxxn.bindingapp.view.base.BaseFragment
import com.google.android.material.tabs.TabLayout
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
        requireDataBinding().tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> findNavController().navigate(R.id.action_main_to_photo)
                    1 -> findNavController().navigate(R.id.action_main_to_tag)
                    2 -> findNavController().navigate(R.id.action_main_to_favorite)
                    else -> findNavController().navigate(R.id.action_main_to_photo)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}