package com.misc.superheroapp.domain.usecase

import com.misc.superheroapp.data.model.HeroInfoResponse
import com.misc.superheroapp.data.utils.Resource
import com.misc.superheroapp.domain.repository.SuperHeroRepository

class GetHeroInfoUseCase(private val heroRepository: SuperHeroRepository) {
    suspend fun execute(id: String): Resource<HeroInfoResponse> {
        return heroRepository.getHeroInfo(id)
    }
}