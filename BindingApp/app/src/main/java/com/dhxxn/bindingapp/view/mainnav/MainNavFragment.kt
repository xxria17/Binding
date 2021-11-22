package com.dhxxn.bindingapp.view.mainnav

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.databinding.FragmentMainNavBinding
import com.dhxxn.bindingapp.view.base.BaseFragment
import com.dhxxn.bindingapp.view.favorite.FavoriteFragment
import com.dhxxn.bindingapp.view.photo.PhotoFragment
import com.dhxxn.bindingapp.view.tag.TagFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainNavFragment : BaseFragment<FragmentMainNavBinding>(R.layout.fragment_main_nav) {

    private val viewModel by activityViewModels<MainNavViewModel>()
    private var flag = 0
    private lateinit var callback: OnBackPressedCallback
    private lateinit var photoFragment: PhotoFragment

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainNavBinding {
        return FragmentMainNavBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUI(flag)
        photoFragment = childFragmentManager.findFragmentById(R.id.photoFragment) as PhotoFragment?
            ?: PhotoFragment.newInstance()
        childFragmentManager.beginTransaction().replace(R.id.main_frameLayout, photoFragment).commitNow()

    }
    override fun init() {
        observeData()

        // 하단 탭 이벤트
        requireDataBinding().mainPhotoBtn.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            transaction.replace(R.id.main_frameLayout, photoFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            flag = 0
            setUI(flag)
        }

        requireDataBinding().mainTagBtn.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            val tagFragment : TagFragment = childFragmentManager.findFragmentById(R.id.tagFragment) as TagFragment?
                ?: TagFragment.newInstance()
            transaction.replace(R.id.main_frameLayout, tagFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            flag = 1
            setUI(flag)
        }

        requireDataBinding().mainFavBtn.setOnClickListener {
            val transaction = childFragmentManager.beginTransaction()
            val favoriteFragment : FavoriteFragment = childFragmentManager.findFragmentById(R.id.favoriteFragment) as FavoriteFragment?
                ?: FavoriteFragment.newInstance()
            transaction.replace(R.id.main_frameLayout, favoriteFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            flag = 2
            setUI(flag)
        }

        // 검색 버튼 이벤트
        requireDataBinding().mainSearchBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainNavFragment_to_searchFragment)
        }

        // 추가 버튼 이벤트
        requireDataBinding().mainPlusBtn.setOnClickListener {
            viewModel.getSelect(requireContext())
        }

        val bottomSheetView = layoutInflater.inflate(R.layout.layout_bottom_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetView)

        // 설정 버튼 이벤트
        requireDataBinding().mainSettingBtn.setOnClickListener {
            bottomSheetDialog.show()
        }

    }

    private fun setUI(flag: Int) {
        when (flag) {
            0 -> {
                setTextStyleActive(requireDataBinding().mainPhotoBtn)
                setTextStyleUnActive(requireDataBinding().mainTagBtn)
                setTextStyleUnActive(requireDataBinding().mainFavBtn)
            }
            1 -> {
                setTextStyleActive(requireDataBinding().mainTagBtn)
                setTextStyleUnActive(requireDataBinding().mainPhotoBtn)
                setTextStyleUnActive(requireDataBinding().mainFavBtn)
            }
            2 -> {
                setTextStyleActive(requireDataBinding().mainFavBtn)
                setTextStyleUnActive(requireDataBinding().mainTagBtn)
                setTextStyleUnActive(requireDataBinding().mainPhotoBtn)
            }
            3 -> {
                setTextStyleUnActive(requireDataBinding().mainPhotoBtn)
                setTextStyleUnActive(requireDataBinding().mainTagBtn)
                setTextStyleUnActive(requireDataBinding().mainFavBtn)
            }
        }

    }

    private fun setTextStyleActive(textView: TextView) {
        textView.setTextColor(Color.parseColor("#000000"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
    }

    private fun setTextStyleUnActive(textView: TextView) {
        textView.setTextColor(Color.parseColor("#9FA0A0"))
        textView.setTypeface(textView.typeface, Typeface.NORMAL)
    }

    private fun observeData() {
        with(viewModel) {
            addPhotoState.observe(viewLifecycleOwner) {
                if (!it) showToast("실패하였습니다. 다시 시도해주세요!")
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

}