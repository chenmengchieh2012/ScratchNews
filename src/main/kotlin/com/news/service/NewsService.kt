package com.news.service

import com.news.entity.TabEntity
import org.apache.logging.log4j.kotlin.Logging
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import javax.lang.model.element.Element

@Service
class NewsService : Logging {


    @Autowired
    lateinit var restTemplate: RestTemplate

    private lateinit var rawNews: String

    private lateinit var latest: TabEntity
    private lateinit var intenation: TabEntity
    private lateinit var commertial: TabEntity
    private lateinit var science: TabEntity
    private lateinit var entertain: TabEntity
    private lateinit var sport: TabEntity
    private lateinit var health: TabEntity

    private val tabs = listOf("最新")

    private val newsRootUrl: String = "https://news.google.com/?hl=zh-TW&gl=TW&ceid=TW:zh-Hant"
    private val newsHost: String = "https://news.google.com"

    var context: String = ""
        get() = field
        set(value) {
            field = value
        }

    fun extractTab(rawNews: String): String {
        val doc = Jsoup.parse(rawNews)
        val hashdoms = doc.select("a:contains(更多「頭條新聞」相關內容)").forEach {
            logger.info(it.allElements)
            return it.attr("href")!!
        }
        return "Not found"
    }

    fun getNews() : String? {
        val latestNewsPage = restTemplate.getForObject(newsRootUrl,String().javaClass)!!
        val newsIndexPageUrl = newsHost + extractTab(latestNewsPage).substring(1)
        rawNews = restTemplate.getForObject(newsIndexPageUrl,String().javaClass)!!
        return rawNews
    }



}