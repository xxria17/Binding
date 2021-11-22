package com.dhxxn.bindingapp.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.*

@ProvidedTypeConverter
class DateTypeConverter {

    @TypeConverter
    fun dateToLong(value: Date): Long {
        return value?.time?.toLong()
    }

    @TypeConverter
    fun longToDate(value: Long): Date {
        return value?.let { Date(it) }
    }
}