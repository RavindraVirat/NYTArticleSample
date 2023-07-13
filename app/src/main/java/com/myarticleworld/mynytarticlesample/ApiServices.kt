package com.myarticleworld.mynytarticlesample

import retrofit2.http.GET

interface ApiServices {

    //    https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key=7cGRjYPcBzlK1gTUhd63eDVkdGgTsYyk
//    @GET("emailed/1.json")
    @GET("emailed/1.json?api-key=7cGRjYPcBzlK1gTUhd63eDVkdGgTsYyk")
    suspend fun getMostEmailedArticles(): ArticleResponse

    //    https://api.nytimes.com/svc/mostpopular/v2/shared/1/facebook.json?api-key=7cGRjYPcBzlK1gTUhd63eDVkdGgTsYyk
//    @GET("shared/1/facebook.json")
    @GET("shared/1/facebook.json?api-key=7cGRjYPcBzlK1gTUhd63eDVkdGgTsYyk")
    suspend fun getMostSharedArticles(): ArticleResponse

    //    https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=7cGRjYPcBzlK1gTUhd63eDVkdGgTsYyk
//    @GET("viewed/7.json")
    @GET("viewed/1.json?api-key=7cGRjYPcBzlK1gTUhd63eDVkdGgTsYyk")
    suspend fun getMostViewedArticles(): ArticleResponse
}