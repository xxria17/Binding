package com.dhxxn.bindingapp.view.detail

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dhxxn.bindingapp.R
import com.dhxxn.bindingapp.databinding.FragmentDetailBinding
import com.dhxxn.bindingapp.view.base.BaseFragment
import com.github.chrisbanes.photoview.PhotoViewAttacher
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val args: DetailFragmentArgs by navArgs()
    private val viewModel by activityViewModels<DetailViewModel>()
    private var flag = false
    private var heartFlag = false

    override fun onCreateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mAttacher = PhotoViewAttacher(requireDataBinding().detailImg)
        mAttacher.setOnViewTapListener { view, x, y ->
            flag = !flag
            setUI(flag)
        }
    }

    private fun setUI(flag : Boolean) {
        if (flag) {
            requireDataBinding().detailToolbar.visibility = View.INVISIBLE
            requireDataBinding().detailLayout.setBackgroundColor(Color.BLACK)
        } else {
            requireDataBinding().detailToolbar.visibility = View.VISIBLE
            requireDataBinding().detailLayout.setBackgroundColor(Color.WHITE)
        }

    }

    override fun init() {
        val id = args.data
        viewModel.getImageInfo(id)

        observeData()

        // 뒤로가기 버튼 이벤트
        requireDataBinding().detailToolbar.findViewById<ImageView>(R.id.detail_back).setOnClickListener {
            findNavController().popBackStack()
        }

        // 지우기 버튼 이벤트
        val bottomDeleteSheet = layoutInflater.inflate(R.layout.layout_delete, null)
        val bottomDeleteDialog = BottomSheetDialog(requireContext())
        bottomDeleteDialog.setContentView(bottomDeleteSheet)
        requireDataBinding().detailToolbar.findViewById<ImageView>(R.id.detail_delete).setOnClickListener {
            bottomDeleteDialog.show()
        }

        // 상세정보 버튼 이벤트
        requireDataBinding().detailToolbar.findViewById<ImageView>(R.id.detail_menu).setOnClickListener {

        }

        val bottomTagSheet = layoutInflater.inflate(R.layout.layout_tag, null)
        val bottomTagDialog = BottomSheetDialog(requireContext())
        bottomTagDialog.setContentView(bottomTagSheet)
        // 태그 버튼 이벤트
        requireDataBinding().detailToolbar.findViewById<ImageView>(R.id.detail_tag).setOnClickListener {
            bottomTagDialog.show()
        }

        // 마음 버튼 이벤트
        requireDataBinding().detailToolbar.findViewById<ImageView>(R.id.detail_heart).setOnClickListener {
            heartFlag = !heartFlag
            if (heartFlag) {
                requireDataBinding().detailToolbar.findViewById<ImageView>(R.id.detail_heart).apply {
                    setImageResource(R.drawable.fill_heart)
                    setColorFilter(Color.RED)
                }
            } else {
                requireDataBinding().detailToolbar.findViewById<ImageView>(R.id.detail_heart).apply {
                    setImageResource(R.drawable.heart)
                    setColorFilter(Color.BLACK)
                }
            }
        }

        // 공유 버튼 이벤트
        requireDataBinding().detailToolbar.findViewById<ImageView>(R.id.detail_share).setOnClickListener {

        }

    }

    private fun observeData() {
        with(viewModel) {
            result.observe(viewLifecycleOwner) {
                Glide.with(requireContext())
                    .load(it.uri)
                    .into(requireDataBinding().detailImg)
            }
        }
    }

}