package com.news

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan
class NewsApplication // this is the class

fun main(args: Array<String>) { // this is main function
	SpringApplication.run(NewsApplication::class.java, *args)
}
