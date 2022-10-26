package com.example.nytimes.data
import java.io.Serializable

data class ArticlesDetails(
    var title: String,
    var abstract: String,
    var byline: String,
    var publishedDate: String,
    var adxKeywords: String,
    var media: List<Medum>,
) : Serializable
