package com.example.nytimes.api

import com.example.nytimes.data.Articles
import retrofit2.http.GET

interface ArticlesApi {

    companion object {
        const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"
    }

    @GET("emailed/7.json?api-key=D7nzuuN2AofvuasqntaDUEDAKBs3g9Nu")
    suspend fun getArticles(): Articles
}