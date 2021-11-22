package com.dhxxn.bindingapp.data.repository.gallery

import com.dhxxn.bindingapp.data.entity.Gallery
import io.reactivex.rxjava3.core.Single

interface GalleryRepository {
    fun getAllPhotos(): Single<List<Gallery>>
}