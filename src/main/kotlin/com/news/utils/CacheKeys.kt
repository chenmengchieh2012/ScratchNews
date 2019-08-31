package com.news.utils

object CacheKeys {
    val generateNewsKey = fun (date: String) : String { return "news.rawdata.$date" }
}