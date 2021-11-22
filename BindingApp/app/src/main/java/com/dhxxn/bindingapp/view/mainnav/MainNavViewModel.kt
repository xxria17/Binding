package com.dhxxn.bindingapp.view.mainnav

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dhxxn.bindingapp.data.entity.Gallery
import com.dhxxn.bindingapp.data.repository.add.AddRepository
import com.dhxxn.bindingapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gun0912.tedimagepicker.builder.TedImagePicker
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainNavViewModel @Inject constructor(
    private val addRepository: AddRepository
) : BaseViewModel() {

    private val _addPhotoState = MutableLiveData<Boolean>()
    val addPhotoState : LiveData<Boolean>
        get() = _addPhotoState

    fun getSelect(context: Context) {
        TedImagePicker.with(context)
            .startMultiImage { uriList ->
                for (uri in uriList) {
                    addRepository.deduplication(uri.toString())
                        .observeOn(Schedulers.io())
                        .subscribe({
                            if (it) {
                                addRepository.saveGallery(Gallery(uri, listOf(), saveTime = getCurrentTime(),false))
                                    .observeOn(Schedulers.io())
                                    .subscribe({
                                        _addPhotoState.postValue(true)
                                    }, {
                                        println("MainNavViewModel subscribe fail ${it.message}")
                                        _addPhotoState.postValue(false)
                                    })
                            }
                        }, {
                            println("MainNavViewModel deduplication error ${it.message}")
                        })

                }
            }
    }

    private fun getCurrentTime(): Date {
        val now = System.currentTimeMillis()
        return Date(now)
    }


}