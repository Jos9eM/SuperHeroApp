package com.misc.superheroapp.data.repository

import com.misc.superheroapp.data.model.HeroInfoResponse
import com.misc.superheroapp.data.repository.dataSource.HeroRemoteDataSource
import com.misc.superheroapp.data.utils.Resource
import com.misc.superheroapp.domain.repository.SuperHeroRepository
import retrofit2.Response

class SuperHeroRepositoryImpl(
    private val newsRemoteDataSource: HeroRemoteDataSource
) : SuperHeroRepository {
    private fun responseToResource(response: Response<HeroInfoResponse>): Resource<HeroInfoResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getHeroInfo(id: String): Resource<HeroInfoResponse> {
        return responseToResource(newsRemoteDataSource.getHeroInfo(id))
    }
}