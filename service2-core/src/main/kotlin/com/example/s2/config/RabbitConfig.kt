package com.example.s2.config

import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {
    companion object {
        const val EXCHANGE = "service.events"
        const val Q_STATUS = "q.status"
        const val Q_DATA = "q.data"
    }

    @Bean fun exchange(): TopicExchange = TopicExchange(EXCHANGE, true, false)
    @Bean fun statusQueue(): Queue = Queue(Q_STATUS, true)
    @Bean fun dataQueue(): Queue = Queue(Q_DATA, true)
    @Bean fun bindStatus(exchange: TopicExchange, statusQueue: Queue): Binding =
        BindingBuilder.bind(statusQueue).to(exchange).with("status")
    @Bean fun bindData(exchange: TopicExchange, dataQueue: Queue): Binding =
        BindingBuilder.bind(dataQueue).to(exchange).with("data")

    @Bean fun messageConverter() = Jackson2JsonMessageConverter()
}
