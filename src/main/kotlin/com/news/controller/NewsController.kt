package com.news.controller

import com.news.schedule.ScratchNewsScheduledService
import com.news.schedule.TitleAnalysisScheduledService
import com.news.service.NewsService
import com.news.service.RedisService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = ["/news"])
class NewsController {

    @Autowired
    lateinit var scratchNewsScheduledService: ScratchNewsScheduledService

    @Autowired
    lateinit var titleAnalysisScheduledService: TitleAnalysisScheduledService


//    @GetMapping(value = ["/test"])
//    fun getNews(): String?{
//        return newService.getNews()
//    }
//
//    @GetMapping(value = ["/test/tab/{tabName}"])
//    fun getTagHashValue(@PathVariable tabName:String): String?{
//        newService.getNews()
//        return newService.getTabPage(tabName)
//    }

    @GetMapping(value = ["/runScratch"])
    fun runScratch(): Map<String, List<String>> {
        return scratchNewsScheduledService.commandModeRetry()
    }

    @GetMapping(value = ["/runAnalysis"])
    fun runAnalysis() {
        titleAnalysisScheduledService.runJiebaCut(Date())
    }

}