package com.example.s1.web

import com.example.dto.DataMessage
import com.example.dto.ServiceType
import com.example.s1.config.RabbitConfig
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/api/v1")
@Validated
class DataController(private val rabbitTemplate: RabbitTemplate) {

    data class SendReq(@field:NotBlank val serviceId: String, @field:NotBlank val text: String)

    @PostMapping("/send")
    fun send(@RequestBody req: SendReq): Map<String, Any> {
        val msg = DataMessage(serviceId = req.serviceId, serviceType = ServiceType.TYPE1, text = req.text)
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, "data", msg)
        return mapOf("status" to "queued")
    }
}
