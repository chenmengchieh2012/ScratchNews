package com.news.configuration

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestConfiguration {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder().build()
    }
}