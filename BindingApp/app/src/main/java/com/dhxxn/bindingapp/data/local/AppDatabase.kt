package com.dhxxn.bindingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dhxxn.bindingapp.data.converter.DateTypeConverter
import com.dhxxn.bindingapp.data.converter.ListTypeConverter
import com.dhxxn.bindingapp.data.converter.UriTypeConverter
import com.dhxxn.bindingapp.data.entity.Favorites
import com.dhxxn.bindingapp.data.entity.Gallery

@Database(entities = [Gallery::class, Favorites::class], version = 1, exportSchema = false)
@TypeConverters(
        value = [
            ListTypeConverter::class,
            DateTypeConverter::class,
            UriTypeConverter::class
        ]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun photoDao(): PhotoDao

    companion object {
        const val DB_NAME = "database-binding"
    }

}