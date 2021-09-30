package com.misc.superheroapp.data.repository.dataSource

import com.misc.superheroapp.data.model.HeroInfoResponse
import retrofit2.Response

interface HeroRemoteDataSource {
    suspend fun getHeroInfo(id: String): Response<HeroInfoResponse>
}