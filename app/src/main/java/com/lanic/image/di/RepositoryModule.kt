package com.lanic.image.di

import com.lanic.image.data.repository.SearchRepository
import com.lanic.image.data.repository.SearchRepositoryImpl
import com.lanic.image.network.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideSearchRepository(searchService: SearchService): SearchRepository {
        return SearchRepositoryImpl(searchService)
    }
}