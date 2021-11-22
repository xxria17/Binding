package com.dhxxn.bindingapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dhxxn.bindingapp.data.entity.Gallery
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PhotoDao {

    @Query(value = "SELECT * FROM Gallery ORDER BY saveTime DESC")
    fun getAll(): Single<List<Gallery>>

    @Query(value = "SELECT * FROM Gallery WHERE uri=:uri")
    fun isOverlap(uri: String): Single<List<Gallery>>

    @Query(value = "SELECT * FROM GALLERY WHERE id=:id")
    fun getDetail(id: Int): Single<Gallery>

    // Single Insert
    @Insert
    fun savePhoto(gallery: Gallery): Completable
}