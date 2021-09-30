package com.misc.superheroapp.domain.repository

import com.misc.superheroapp.data.model.HeroInfoResponse
import com.misc.superheroapp.data.utils.Resource

interface SuperHeroRepository {
    suspend fun getHeroInfo(id: String): Resource<HeroInfoResponse>
}
