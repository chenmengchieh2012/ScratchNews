package com.news

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@ComponentScan
@EnableJpaAuditing
@EnableJpaRepositories
@EntityScan
@EnableScheduling
class NewsApplication : SpringBootServletInitializer()  // this is the class

fun main(args: Array<String>) { // this is main function
	SpringApplication.run(NewsApplication::class.java, *args)
}
