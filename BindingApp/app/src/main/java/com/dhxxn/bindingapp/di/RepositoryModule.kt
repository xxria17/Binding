package com.dhxxn.bindingapp.di

import com.dhxxn.bindingapp.data.repository.add.AddRepository
import com.dhxxn.bindingapp.data.repository.add.AddRepositoryImpl
import com.dhxxn.bindingapp.data.repository.detail.DetailRepository
import com.dhxxn.bindingapp.data.repository.detail.DetailRepositoryImpl
import com.dhxxn.bindingapp.data.repository.gallery.GalleryRepository
import com.dhxxn.bindingapp.data.repository.gallery.GalleryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsAddRepository(
        repositoryImpl: AddRepositoryImpl
    ): AddRepository

    @Binds
    abstract fun bindsGalleryRepository(
        repositoryImpl: GalleryRepositoryImpl
    ): GalleryRepository

    @Binds
    abstract fun bindsDetailRepository(
        repositoryImpl: DetailRepositoryImpl
    ): DetailRepository
}