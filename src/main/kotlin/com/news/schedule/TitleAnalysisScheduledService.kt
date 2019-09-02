package com.news.schedule

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.news.entity.CutTitle
import com.news.entity.RawTitle
import com.news.repository.CutTitleRepository
import com.news.repository.RawTitleRepository
import com.news.service.JiebaNativeService
import com.news.service.RedisService
import com.news.utils.CacheKeys
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class TitleAnalysisScheduledService: Logging {

    @Autowired
    lateinit var jiebaNativeService: JiebaNativeService

    @Autowired
    lateinit var redisService: RedisService

    @Autowired
    lateinit var cutTitleRepository: CutTitleRepository

    @Autowired
    lateinit var rawTitleRepository: RawTitleRepository


    @Scheduled(cron="0 0 * ? * *")
    fun analysisTopic() {
        logger.info("=============== schedule Analysis Start ================")
        runJiebaCut(Date())
        logger.info("=============== schedule Analysis End ================")
    }

    fun runJiebaCut(date: Date){


        val sdf = SimpleDateFormat("ddMMyyyy")
        val currentDate = sdf.format(date)
        val mapper = jacksonObjectMapper()

        for( iter in (0..24)){
            val hour = if(iter < 10) "0$iter" else "$iter"


            val rawstr = redisService.getNews("$currentDate$hour")
            rawstr?.let {
                logger.info("rawstr: $rawstr")
                val rawObject: Map<String, List<String>> = mapper.readValue(it)
                rawObject.forEach() { tagEntry ->

                    var totalMap: MutableMap<String,Int> = mutableMapOf()

                    tagEntry.value.forEach() {title ->
                        @Suppress("UNCHECKED_CAST")
                        val jiebaResult : List<String> = jiebaNativeService.runJieba(title) as List<String>
                        logger.info(jiebaResult)
                        jiebaResult.forEach(){item ->
                            if(totalMap.containsKey(item)){
                                totalMap.replace(item,totalMap.getValue(item)+1)
                            }else{
                                totalMap.put(item,1)
                            }
                        }
                    }
                    logger.info(totalMap.toString())
                    val sdf = SimpleDateFormat("ddMMyyyyhh")
                    var cutTitle = CutTitle(
                            null,
                            Date(),
                            sdf.parse("$currentDate$hour"),
                            tagEntry.key,
                            mapper.writeValueAsString(totalMap)
                    )
                    cutTitleRepository.save(cutTitle)

                    var rawTitle = RawTitle(
                            null,
                            sdf.parse("$currentDate$hour"),
                            tagEntry.key,
                            mapper.writeValueAsString(tagEntry.value)
                    )
                    rawTitleRepository.save(rawTitle)
                }

                val ret = redisService.deleteNews("$currentDate$hour")
                logger.info(ret)

            }
        }





    }
}