package com.lanic.brandi.di

import com.lanic.brandi.data.repository.SearchRepository
import com.lanic.brandi.data.repository.SearchRepositoryImpl
import com.lanic.brandi.network.SearchService
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