package com.example.s1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class S1Application

fun main(args: Array<String>) = runApplication<S1Application>(*args)
