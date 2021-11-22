package com.dhxxn.bindingapp.data.repository.detail

import com.dhxxn.bindingapp.data.entity.Gallery
import io.reactivex.rxjava3.core.Single

interface DetailRepository {
    fun getDetailImg(id: Int): Single<Gallery>
}