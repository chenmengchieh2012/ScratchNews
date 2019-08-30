package com.news

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan
class DemoApplication // this is the class

fun main(args: Array<String>) { // this is main function
	runApplication<DemoApplication>(*args)
}
