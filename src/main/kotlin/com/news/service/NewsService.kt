package com.news.service

interface NewsService {
    fun getTabPage(tab: String): String?
    fun getNews(): String?
    fun getTitles(): Map<String, List<String>>
}