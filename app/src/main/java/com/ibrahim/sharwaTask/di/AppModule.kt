package com.ibrahim.sharwaTask.di

import com.ibrahim.sharwaTask.cart.data.ItemsRemoteDataSource
import com.ibrahim.sharwaTask.cart.data.ItemsRepositoryImpl
import com.ibrahim.sharwaTask.cart.domain.repository.ItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemsRemoteDataSource(): ItemsRemoteDataSource {
        return ItemsRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideItemsRepository (itemsRemoteDataSource: ItemsRemoteDataSource): ItemsRepository {
        return ItemsRepositoryImpl(itemsRemoteDataSource)
    }
}