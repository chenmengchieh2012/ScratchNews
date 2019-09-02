package com.news.service

import org.apache.logging.log4j.core.Logger
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.InputStreamReader
import java.nio.charset.Charset


@Service
class JiebaNativeService: Logging {

    @Value("\${news.jieBascript:C:\\Users\\Mj\\Desktop\\news\\jiebaExtract.py}")
    lateinit var jiebaExtractScriptPath: String

    fun runJieba(text: String): List<Any> {
        logger.info(jiebaExtractScriptPath)
        val arg = arrayOf("python", jiebaExtractScriptPath, text)
        val p = Runtime.getRuntime().exec(arg)
        val inreader = BufferedReader(InputStreamReader(p.inputStream, Charset.forName("UTF-8")))
        //val errreader = BufferedReader(InputStreamReader(p.errorStream, Charset.forName("UTF-8")))
        return inreader.lines().toArray().toList()

    }

}