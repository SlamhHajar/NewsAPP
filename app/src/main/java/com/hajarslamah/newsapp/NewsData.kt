package com.hajarslamah.newsapp

import com.google.gson.internal.bind.DateTypeAdapter
import java.util.*

data class NewsData (
    val ID:Int,
    val title:String,
    val details:String,
    val date: Date,
    val image:String,
    val cat_ID:Int

)
