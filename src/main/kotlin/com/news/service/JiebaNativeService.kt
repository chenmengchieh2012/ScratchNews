package com.news.service

import org.apache.logging.log4j.core.Logger
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.InputStreamReader
import java.nio.charset.Charset


@Service
class JiebaNativeService: Logging {


    fun runJieba(text: String): List<Any> {
        val pyfilePath = this.javaClass.getResource("/jiebaExtract.py").path.substring(1)
        logger.info(pyfilePath)
        val arg = arrayOf("python", pyfilePath, text)
        val p = Runtime.getRuntime().exec(arg)
        val inreader = BufferedReader(InputStreamReader(p.inputStream, Charset.forName("UTF-8")))
        //val errreader = BufferedReader(InputStreamReader(p.errorStream, Charset.forName("UTF-8")))
        return inreader.lines().toArray().toList()

    }

}