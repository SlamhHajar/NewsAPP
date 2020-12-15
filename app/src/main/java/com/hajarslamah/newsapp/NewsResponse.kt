package com.hajarslamah.newsapp

import com.google.gson.annotations.SerializedName

class NewsResponse {
  //  @SerializedName(new)
    lateinit var newItems:List<NewsData>
}