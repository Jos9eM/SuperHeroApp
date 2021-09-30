package com.misc.superheroapp.presentation.di

import com.misc.superheroapp.domain.repository.SuperHeroRepository
import com.misc.superheroapp.domain.usecase.GetHeroInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetHeroUseCase(heroRepository: SuperHeroRepository): GetHeroInfoUseCase {
        return GetHeroInfoUseCase(heroRepository)
    }
}