package com.dhxxn.bindingapp.di

import android.app.Application
import androidx.room.Room
import com.dhxxn.bindingapp.data.converter.DateTypeConverter
import com.dhxxn.bindingapp.data.converter.ListTypeConverter
import com.dhxxn.bindingapp.data.converter.UriTypeConverter
import com.dhxxn.bindingapp.data.local.AppDatabase
import com.dhxxn.bindingapp.data.local.AppDatabase.Companion.DB_NAME
import com.dhxxn.bindingapp.data.local.PhotoDao
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .addTypeConverter(ListTypeConverter(provideGson()))
            .addTypeConverter(DateTypeConverter())
            .addTypeConverter(UriTypeConverter())
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideGallery(appDatabase: AppDatabase): PhotoDao = appDatabase.photoDao()

}