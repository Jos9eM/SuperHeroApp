package com.misc.superheroapp.data.repository.dataSourceImpl

import com.misc.superheroapp.data.api.HerosApiServices
import com.misc.superheroapp.data.model.HeroInfoResponse
import com.misc.superheroapp.data.repository.dataSource.HeroRemoteDataSource
import retrofit2.Response

class HeroRemoteDataSourceImpl(
    private val herosApiServices: HerosApiServices
) : HeroRemoteDataSource {
    override suspend fun getHeroInfo(id: String): Response<HeroInfoResponse> {
        return herosApiServices.getHeroInfo(id)
    }
}