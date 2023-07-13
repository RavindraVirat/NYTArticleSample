package com.myarticleworld.mynytarticlesample

import android.util.Log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun getArticles() = flow {
        emit(NetworkResult.Loading(true))

        val respEmailedArticles = apiServices.getMostEmailedArticles()

        if (respEmailedArticles.status == "OK") {
            if (respEmailedArticles.Articles.size > 3) emit(
                NetworkResult.Success(
                    respEmailedArticles.Articles.take(3)
                )
            )
            else emit(NetworkResult.Success(respEmailedArticles.Articles))
        } else emit(NetworkResult.Failure("Error"))

    }.catch {
        emit(NetworkResult.Failure("Unknown Error"))
        it.message?.let { it1 -> Log.e("RRRR", it1) }
    }

    suspend fun getArticlesNew() = flow {
        emit(NetworkResult.Loading(true))

        val respSharedArticles = apiServices.getMostSharedArticles()
        val respEmailedArticles = apiServices.getMostEmailedArticles()
        val respViewedArticles = apiServices.getMostViewedArticles()

        val list = mutableListOf<Article>()

        if (respSharedArticles.status == "OK") {
            list.add(getSectionName("Most Shared Articles"))
            list.addAll(respSharedArticles.Articles.take(3))
        }

        if (respEmailedArticles.status == "OK") {
            list.add(getSectionName("Most Emailed Articles"))
            list.addAll(respEmailedArticles.Articles.take(3))
        }

        if (respViewedArticles.status == "OK") {
            list.add(getSectionName("Most Viewed Articles"))
            list.addAll(respViewedArticles.Articles.take(3))
        }

        if (list.isNotEmpty())
            emit(NetworkResult.Success(list.toList()))
        else
            emit(NetworkResult.Failure("Error"))
    }.catch {
        emit(NetworkResult.Failure(it.localizedMessage))
        it.message?.let { it1 -> Log.e("RRRR", it1) }
    }

    private fun getSectionName(sectionName: String): Article {
        val article = Article()
        article.type = "Section"
        article.title = sectionName
        return article
    }

}