import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.7.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.0"
	kotlin("plugin.spring") version "1.3.0"
	kotlin("plugin.jpa") version "1.3.0"
}

group = "com.news"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.21")
	implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.21")
	// https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-core
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//restTemplate
	implementation("org.springframework.boot:spring-boot-starter-web")

	//jsoup
	implementation("org.jsoup:jsoup:1.11.3")

	//log4j
	implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
	implementation("org.apache.logging.log4j:log4j-api:2.11.1")
	implementation("org.apache.logging.log4j:log4j-core:2.11.1")

	//redis + redisTemplate
	implementation("org.springframework.data:spring-data-redis:2.1.8.RELEASE")
	implementation("redis.clients:jedis:2.9.0")

	//https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	//https://mvnrepository.com/artifact/org.postgresql/postgresql
	implementation("org.postgresql:postgresql")



	//https://mvnrepository.com/artifact/org.hibernate/hibernate-core
	implementation("org.hibernate:hibernate-core:5.4.2.Final")
	//https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager
	implementation("org.hibernate:hibernate-entitymanager:5.4.2.Final")

	//https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa
	//implementation("org.springframework.data:spring-data-jpa:2.1.9.RELEASE")
	//https://mvnrepository.com/artifact/org.springframework/spring-aspects
	//implementation("org.springframework:spring-aspects:5.1.4.RELEASE")



	//https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.7")


}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
