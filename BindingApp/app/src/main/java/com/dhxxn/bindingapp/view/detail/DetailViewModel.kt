package com.dhxxn.bindingapp.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dhxxn.bindingapp.data.entity.Gallery
import com.dhxxn.bindingapp.data.repository.detail.DetailRepository
import com.dhxxn.bindingapp.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
) : BaseViewModel() {

    private val _result = MutableLiveData<Gallery>()
    val result : LiveData<Gallery>
        get() = _result

    fun getImageInfo(id : Int) {
        println("DetailViewModel getImage id $id")
        repository.getDetailImg(id)
            .observeOn(Schedulers.io())
            .subscribe({
                _result.postValue(it)
                println("DetailViewModel success $it")
            }, {
                println("DetailViewModel error ${it.message}")
            })
    }
}