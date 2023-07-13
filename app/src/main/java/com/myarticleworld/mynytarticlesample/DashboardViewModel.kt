package com.myarticleworld.mynytarticlesample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject public constructor(private val repository: Repository) :
    ViewModel() {

    private var _articlesList = MutableLiveData<NetworkResult<List<Article>>>()
    val articlesList: LiveData<NetworkResult<List<Article>>> = _articlesList

    fun fetchArticles() {
        viewModelScope.launch {
            repository.getArticlesNew().collect {
                _articlesList.value = it
            }

//            val response = repository.getArticlesNew()
        }
    }
}