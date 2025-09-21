package com.example.s2.mq

import com.example.dto.DataMessage
import com.example.dto.StatusMessage
import com.example.s2.model.ActivityEntity
import com.example.s2.model.MessageEntity
import com.example.s2.model.UserEntity
import com.example.s2.repo.ActivityRepository
import com.example.s2.repo.MessageRepository
import com.example.s2.repo.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Component
class Consumers(
    private val users: UserRepository,
    private val messages: MessageRepository,
    private val activities: ActivityRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional
    @RabbitListener(queues = ["q.status"])
    fun onStatus(msg: StatusMessage) {
        var user = users.findByExternalId(msg.serviceId)
        if (user == null) {
            user = users.save(
                UserEntity(
                    externalId = msg.serviceId,
                    serviceType = msg.serviceType,
                    lastStatus = msg.status,
                    lastSeenAt = msg.timestamp
                )
            )
            log.info("New user registered: {}", msg.serviceId)
        } else {
            user.lastStatus = msg.status
            user.lastSeenAt = msg.timestamp
            users.save(user)
        }
        activities.save(ActivityEntity(user = user!!, status = msg.status, occurredAt = msg.timestamp))
    }

    @Transactional
    @RabbitListener(queues = ["q.data"])
    fun onData(msg: DataMessage) {
        val user = users.findByExternalId(msg.serviceId)
            ?: users.save(
                UserEntity(
                    externalId = msg.serviceId,
                    serviceType = msg.serviceType,
                    lastStatus = com.example.dto.Status.ACTIVE,
                    lastSeenAt = Instant.now()
                )
            )
        messages.save(MessageEntity(user = user, text = msg.text, receivedAt = msg.timestamp))
    }
}
