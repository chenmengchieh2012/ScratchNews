package com.news.service

import com.news.utils.CacheKeys.generateNewsKey
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class RedisServiceImpl: Logging,RedisService {

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String,String>

    override
    fun saveNews(value: String) {
        val sdf = SimpleDateFormat("ddMMyyyyhh")
        val currentDate = sdf.format(Date())
        val redisKey = generateNewsKey(currentDate)
        redisTemplate.opsForValue().set(redisKey,value)
        logger.info(redisKey)
    }

    override
    fun getNews(year: Int, month: Int, date: Int, hour: Int) : String?{
        val sdf = SimpleDateFormat("ddMMyyyyhh")
        val date = Date(year,month,date,hour,0)
        val currentDate = sdf.format(date)
        val redisKey = generateNewsKey(currentDate)
        return redisTemplate.opsForValue().get(redisKey)
    }

    override
    fun getNews(date: String) : String?{
        val redisKey = generateNewsKey(date)
        return redisTemplate.opsForValue().get(redisKey)
    }

}