package com.misc.superheroapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.misc.superheroapp.data.model.HeroInfoResponse
import com.misc.superheroapp.data.utils.Resource
import com.misc.superheroapp.domain.usecase.GetHeroInfoUseCase
import com.misc.superheroapp.presentation.utils.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesInfoViewModel @Inject constructor(
    private val app: Application,
    private val getHeroInfoUseCase: GetHeroInfoUseCase,
    state: SavedStateHandle
) : AndroidViewModel(app) {

    val span = 15
    var enable = true

    private val _listHeroes =
        state.getLiveData<MutableList<HeroInfoResponse>>("heroesList", mutableListOf())
    val listHeroes: LiveData<MutableList<HeroInfoResponse>> get() = _listHeroes

    private val _heroesInfo = MutableLiveData<Resource<HeroInfoResponse>>()
    val heroesInfo: LiveData<Resource<HeroInfoResponse>> get() = _heroesInfo

    fun getHeroesInfo() = viewModelScope.launch(Dispatchers.IO) {
        if (enable) {
            _heroesInfo.postValue(Resource.Loading())
            try {
                enable = false
                val listSize = listHeroes.value?.size
                val countStart =
                    if (listSize == 0) 1 else (listSize?.plus(1))
                val countEnd = countStart?.plus(span)
                for (i in countStart!!..countEnd!!) {
                    if (i <= 731) {  // Ids Total Number
                        if (isNetworkAvailable(app)) {
                            val apiResult = getHeroInfoUseCase.execute(i.toString())
                            _heroesInfo.postValue(apiResult)
                        } else {
                            _heroesInfo.postValue(Resource.Error("No hay Red Disponible"))
                            return@launch
                        }
                    }
                }
                enable = true
            } catch (e: Exception) {
                e.printStackTrace()
                _heroesInfo.postValue(e.message?.let { Resource.Error(it) })
            }
        }
    }

    fun updateListHeroes(hero: HeroInfoResponse) {
        if (_listHeroes.value?.contains(hero) == false) {
            _listHeroes.value?.add(hero)
        }
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        return context.isNetworkAvailable()
    }
}