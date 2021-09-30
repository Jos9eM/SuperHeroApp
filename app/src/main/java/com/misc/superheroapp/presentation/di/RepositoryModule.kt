package com.misc.superheroapp.presentation.di

import com.misc.superheroapp.data.repository.SuperHeroRepositoryImpl
import com.misc.superheroapp.data.repository.dataSource.HeroRemoteDataSource
import com.misc.superheroapp.domain.repository.SuperHeroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideHeroRepository(
        heroRemoteDataSource: HeroRemoteDataSource
    ): SuperHeroRepository {
        return SuperHeroRepositoryImpl(heroRemoteDataSource)
    }
}