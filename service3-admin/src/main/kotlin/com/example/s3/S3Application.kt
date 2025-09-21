package com.example.s3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class S3Application

fun main(args: Array<String>) = runApplication<S3Application>(*args)
