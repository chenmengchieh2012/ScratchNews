package com.news.service

import com.news.entity.TabEntity
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.kotlin.Logging
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class NewsServiceImpl : Logging,NewsService {


    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var redisService: RedisService

    private lateinit var rawNews: String


    private lateinit var latest: TabEntity
    private lateinit var intenation: TabEntity
    private lateinit var commertial: TabEntity
    private lateinit var science: TabEntity
    private lateinit var entertain: TabEntity
    private lateinit var sport: TabEntity
    private lateinit var health: TabEntity

    private val tabs = listOf("最新","國際","商業","科學與科技","娛樂","體育","健康")

    private val newsGETparameter = "?hl=zh-TW&gl=TW&ceid=TW:zh-Hant"
    private val newsHost: String = "https://news.google.com"
    private var newsIndexPageValue: String? = null


    private fun getMainPage(rawNews: String): String? {
        val doc = Jsoup.parse(rawNews)
        lateinit var hashValue : String
        doc.select("a:contains(更多「頭條新聞」相關內容)").forEach {
//            logger.info(it.allElements)
            val tmpHashValue = it.attr("href")
            if(!tmpHashValue.equals("Not found")) hashValue = tmpHashValue
        }
        return hashValue
    }

    private fun getTagValue(rawNews: String ,tag: String): String? {
        val doc = Jsoup.parse(rawNews)
        logger.info(rawNews.length)
        lateinit var hashValue : String
        doc.select("span:contains($tag)").forEach {
//            logger.info(it.allElements)
            val tmpHashValue = it.parents().parents().parents().attr("aria-controls")
            logger.info(tmpHashValue)
            if(tmpHashValue.length != 0) hashValue = tmpHashValue
        }
        return hashValue
    }

    private fun getNewsTitle(rawNews: String): List<String>?{
//        a.DY5T1d
        val doc = Jsoup.parse(rawNews)
        logger.info(rawNews.length)
        var titles: MutableList<String> = mutableListOf<String>()
        doc.select("a.DY5T1d").forEach {
            titles.add(it.html())
            logger.info("get: " + it.html())
        }
        logger.info(titles.toString())
        return titles
    }

    override
    fun getTitles(): Map<String, List<String>> {
        var titlesmap: MutableMap<String, List<String>> = mutableMapOf<String, List<String>>()
        runBlocking <Unit> {
            val lstOfReturnData = mutableListOf<Job>()
            val coroutinesScratch  = tabs.forEach { tab ->
                val job = launch {
                    val dom: String? = getTabPage(tab)
                    dom?.let { logger.info("domlength: " + dom.length) }
                    val titles: List<String>? = dom?.let { getNewsTitle(it) }
                    titles?.let { realtitle ->
                        titlesmap.put(tab,realtitle)
                    }
                }
                lstOfReturnData.add(job)
            }
            lstOfReturnData.forEach{ it.join() }
        }
        logger.info(titlesmap.toString())
        redisService.saveNews(titlesmap.toString())
        return titlesmap
    }

    override
    fun getTabPage(tab: String): String? {
        val newsTabPageUrl = newsIndexPageValue?.let {
            newsHost + newsIndexPageValue + "/sections/" + getTagValue(rawNews,tab)?.substring(3) + newsGETparameter
        }
        newsTabPageUrl?.let { logger.info(it) }
        return newsTabPageUrl?.let { restTemplate.getForObject(it, String().javaClass) }
    }

    override
    fun getNews() : String? {
        val latestNewsPage = restTemplate.getForObject(newsHost+"/"+newsGETparameter,String().javaClass)
        this.newsIndexPageValue = latestNewsPage?.let { getMainPage(it)?.substring(1)?.substringBefore("?") }
        this.rawNews = newsIndexPageValue?.let {
            restTemplate.getForObject(newsHost + it +"/"+newsGETparameter, String().javaClass)
        }.toString()
        return rawNews
    }





}