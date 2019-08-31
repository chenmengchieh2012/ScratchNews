package com.news.service

interface RedisService {
    fun saveNews(value: String)
    fun getNews(year: Int, month: Int, date: Int, hour: Int) : String?
}