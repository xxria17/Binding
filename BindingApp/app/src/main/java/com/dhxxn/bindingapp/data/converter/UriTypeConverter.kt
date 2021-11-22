package com.dhxxn.bindingapp.data.converter

import android.net.Uri
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class UriTypeConverter {

    @TypeConverter
    fun uriToString(value: Uri): String {
        return value.toString()
    }

    @TypeConverter
    fun stringToUri(value: String): Uri {
        return Uri.parse(value)
    }
}