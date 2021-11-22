package com.dhxxn.bindingapp.view.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.data.entity.Gallery
import com.dhxxn.bindingapp.databinding.FragmentPhotoBinding
import com.dhxxn.bindingapp.view.base.BaseFragment
import com.dhxxn.bindingapp.view.mainnav.MainNavFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : BaseFragment<FragmentPhotoBinding>(R.layout.fragment_photo) {

    private val viewModel by activityViewModels<PhotoViewModel>()
    private val adapter = PhotoAdapter()

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPhotoBinding {
        return FragmentPhotoBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun init() {
        adapter.apply { onClick = this@PhotoFragment::goToDetail }
        requireDataBinding().photoList.adapter = adapter
        requireDataBinding().photoList.layoutManager = GridLayoutManager(requireContext(), 4)

        viewModel.getData()
        observeData()
    }

    private fun observeData() {
        with(viewModel) {
            photoList.observe(viewLifecycleOwner) {
                adapter.addItems(it)
            }
        }
    }

    private fun goToDetail(info: Gallery) {
        val action = MainNavFragmentDirections.actionMainNavFragmentToDetailFragment(info.id)
        requireView().findNavController().navigate(action)
    }

    companion object {
        @JvmStatic
        fun newInstance() = PhotoFragment()
    }

}