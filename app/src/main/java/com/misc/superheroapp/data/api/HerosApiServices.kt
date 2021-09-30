package com.misc.superheroapp.data.api

import com.misc.superheroapp.BuildConfig
import com.misc.superheroapp.data.model.HeroInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HerosApiServices {
    @GET("${BuildConfig.API_KEY}/{id}")
    suspend fun getHeroInfo(
        @Path("id") id: String
    ): Response<HeroInfoResponse>
}