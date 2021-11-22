package com.dhxxn.bindingapp.data.repository.detail

import com.dhxxn.bindingapp.data.entity.Gallery
import com.dhxxn.bindingapp.data.local.PhotoDao
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val photoDao: PhotoDao
): DetailRepository {
    override fun getDetailImg(id: Int): Single<Gallery> {
        return photoDao.getDetail(id)
    }
}