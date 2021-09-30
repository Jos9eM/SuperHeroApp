package com.misc.superheroapp.presentation.di

import com.misc.superheroapp.BuildConfig
import com.misc.superheroapp.data.api.HerosApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideHeroApiService(retrofit: Retrofit): HerosApiServices{
        return retrofit.create(HerosApiServices::class.java)
    }
}