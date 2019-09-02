package com.news.schedule

import com.news.service.NewsService
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.*

@Service
class ScratchNewsScheduledService: Logging {

    @Autowired
    lateinit var newService: NewsService

    @Scheduled(cron="0 0 0/3 ? * *")
    fun scheduledGetNews() {
        logger.info("=============== schedule Scratch Start ================")
        newService.getNews()
        newService.getTitles()
        logger.info("=============== schedule Scratch End ================")
    }

    fun commandModeRetry(): Map<String, List<String>>{
        newService.getNews()
        return newService.getTitles()
    }


}