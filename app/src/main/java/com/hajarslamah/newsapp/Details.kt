package com.hajarslamah.newsapp

import com.google.gson.annotations.SerializedName

data class Details(
    @SerializedName("onlyNew")  var newDetail:List<NewsData>)