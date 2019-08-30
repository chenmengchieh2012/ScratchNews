package com.news.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class NewsService {

    @Autowired
    lateinit var restTemplate: RestTemplate

    var context: String = ""
        get() = field
        set(value) {
            field = value
        }

    fun getNews() : String? {
        var url: String = "https://www.google.com"
        return restTemplate.getForObject(url,String().javaClass)
    }

}