package com.dhxxn.bindingapp.data.repository.add

import com.dhxxn.bindingapp.data.entity.Gallery
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AddRepository {
    fun deduplication(uri: String): Single<Boolean>
    fun saveGallery(gallery: Gallery): Completable
}