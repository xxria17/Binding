package com.dhxxn.bindingapp.data.repository.gallery

import com.dhxxn.bindingapp.data.entity.Gallery
import com.dhxxn.bindingapp.data.local.PhotoDao
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val photoDao: PhotoDao
): GalleryRepository {
    override fun getAllPhotos(): Single<List<Gallery>> {
        return photoDao.getAll()
    }
}