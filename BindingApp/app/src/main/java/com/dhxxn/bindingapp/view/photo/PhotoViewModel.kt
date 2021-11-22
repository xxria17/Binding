package com.dhxxn.bindingapp.view.photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dhxxn.bindingapp.data.entity.Gallery
import com.dhxxn.bindingapp.data.repository.gallery.GalleryRepository
import com.dhxxn.bindingapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val galleryRepository: GalleryRepository
) : BaseViewModel() {

    private val _photoList = MutableLiveData<List<Gallery>>()
    val photoList : LiveData<List<Gallery>>
        get() = _photoList

    fun getData() {
        compositeDisposable.add(
            galleryRepository.getAllPhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    println("PhotoViewModel getData $it")
                    if (it.isEmpty() || it == null) {
                        println("PhotoViewModel getData null!!!")
                    } else {
                        _photoList.postValue(it)
                    }
                }, {
                    println("PhotoViewModel getData error ${it.message}")
                })
        )
    }
}