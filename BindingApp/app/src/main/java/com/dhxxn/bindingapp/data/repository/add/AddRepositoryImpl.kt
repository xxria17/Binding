package com.dhxxn.bindingapp.data.repository.add

import com.dhxxn.bindingapp.data.entity.Gallery
import com.dhxxn.bindingapp.data.local.PhotoDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AddRepositoryImpl @Inject constructor(
    private val photoDao: PhotoDao
): AddRepository {
    override fun deduplication(uri: String): Single<Boolean> {
        return Single.create { emitter ->
            photoDao.isOverlap(uri)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    println("AddRepositoryImpl ${it.size}")
                    if (it.isNotEmpty() || it.size != 0) {
                        emitter.onSuccess(false)
                    } else {
                        emitter.onSuccess(true)
                    }
                }, {
                    emitter.onError(it)
                })
        }
    }

    override fun saveGallery(gallery: Gallery): Completable {
        return photoDao.savePhoto(gallery)
    }

}