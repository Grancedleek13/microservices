package com.example.s3.service

import com.example.dto.ServiceType
import com.example.dto.Status
import com.example.dto.StatusMessage
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class Heartbeat(
    private val rabbitTemplate: RabbitTemplate,
    @Value("\${app.service-id:ms3-ADMIN-001}") private val serviceId: String
) {
    @Scheduled(fixedDelayString = "\${app.heartbeat-ms:7000}")
    fun send() {
        val msg = StatusMessage(serviceId, ServiceType.TYPE3, Status.ACTIVE, Instant.now())
        rabbitTemplate.convertAndSend("service.events", "status", msg)
    }
}
