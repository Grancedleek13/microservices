package com.example.s1.service

import com.example.dto.ServiceType
import com.example.dto.Status
import com.example.dto.StatusMessage
import com.example.s1.config.RabbitConfig
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class HeartbeatScheduler(
    private val rabbitTemplate: RabbitTemplate,
    @Value("\${app.service-id}") private val serviceId: String
) {
    @Scheduled(fixedDelayString = "\${app.heartbeat-ms:5000}")
    fun sendHeartbeat() {
        val msg = StatusMessage(serviceId, ServiceType.TYPE1, Status.ACTIVE, Instant.now())
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "status", msg)
    }
}
