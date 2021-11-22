package com.dhxxn.bindingapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favorites(
    val photoId: Int
)  {
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}