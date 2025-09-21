package com.example.s3.config

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class Beans {
    @Bean fun messageConverter() = Jackson2JsonMessageConverter()
    @Bean fun webClient(): WebClient = WebClient.builder().build()
}
