package com.news.controller

import com.news.entity.TabEntity
import com.news.service.NewsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/news")
class NewsController {

    @Autowired
    lateinit var newService: NewsService



    @GetMapping(value = "/test")
    fun getNews(): String?{
        return newService.getNews()
    }

    @GetMapping(value = "/test/tab/{tabName}")
    fun getTagHashValue(@PathVariable tabName:String): String?{
        return newService.getTabPage(tabName)
    }

}