package com.misc.superheroapp.presentation.di

import com.misc.superheroapp.data.api.HerosApiServices
import com.misc.superheroapp.data.repository.dataSource.HeroRemoteDataSource
import com.misc.superheroapp.data.repository.dataSourceImpl.HeroRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providesHeroRemoteDataSource(heroApiServices: HerosApiServices): HeroRemoteDataSource {
        return HeroRemoteDataSourceImpl(heroApiServices)
    }
}