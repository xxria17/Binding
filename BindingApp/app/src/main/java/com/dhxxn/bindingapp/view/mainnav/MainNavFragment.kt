package com.dhxxn.bindingapp.view.mainnav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.databinding.FragmentMainNavBinding
import com.dhxxn.bindingapp.view.base.BaseFragment
import com.dhxxn.bindingapp.view.favorite.FavoriteFragment
import com.dhxxn.bindingapp.view.photo.PhotoFragment
import com.dhxxn.bindingapp.view.photo.PhotoFragment_GeneratedInjector
import com.dhxxn.bindingapp.view.tag.TagFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainNavFragment : BaseFragment<FragmentMainNavBinding>(R.layout.fragment_main_nav) {
    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainNavBinding {
        return FragmentMainNavBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.main_frameLayout, PhotoFragment()).commitAllowingStateLoss()

        requireDataBinding().tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val transaction = childFragmentManager.beginTransaction()
                when (tab?.position) {
                    0 ->  transaction.replace(R.id.main_frameLayout, PhotoFragment())
                    1 -> transaction.replace(R.id.main_frameLayout, TagFragment())
                    2 -> transaction.replace(R.id.main_frameLayout, FavoriteFragment())
                    else -> println("MainNavFragment onTabSelectedListener error")
                }
                transaction.addToBackStack(null)
                transaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        requireDataBinding().mainSearchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainNavFragment_to_searchFragment)
        }


    }

}