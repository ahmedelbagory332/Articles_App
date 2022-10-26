package com.example.nytimes.data


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Articles(
    var status: String,
    var copyright: String,
    @SerializedName("num_results")
    var numResults: Long,
    var results: List<Result>,
)
 class Result{
    var id: Long= 0
    @SerializedName("published_date")
    var publishedDate: String = ""
    @SerializedName("adx_keywords")
    var adxKeywords: String= ""
    var byline: String= ""
    var abstract: String= ""
    var title: String= ""
    var media: List<Medum> = listOf()
 }

data class Medum(
    var type: String,
    var subtype: String,
    var caption: String,
    var copyright: String,
    @SerializedName("approved_for_syndication")
    var approvedForSyndication: Long,
    @SerializedName("media-metadata")
    var mediaMetadata: List<Metadaum>,
): Serializable

data class Metadaum(
    var url: String,
    var format: String,
    var height: Long,
    var width: Long,
): Serializable
