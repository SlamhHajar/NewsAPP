package com.hajarslamah.newsapp

import com.google.gson.annotations.SerializedName

class NewsResponse {
  @SerializedName("allnews")
    lateinit var newItems:List<NewsData>
}