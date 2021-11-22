package com.dhxxn.bindingapp.data.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Gallery(
    val uri: Uri,
    val tags: List<Tags>,
    val saveTime: Date,
    val fav: Boolean
) {
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}
