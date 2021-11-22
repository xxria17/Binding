package com.dhxxn.bindingapp.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.dhxxn.bindingapp.data.entity.Tags
import com.google.gson.Gson

@ProvidedTypeConverter
class ListTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun listToJson(value: List<Tags>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Tags> {
        return gson.fromJson(value, Array<Tags>::class.java).toList()
    }
}