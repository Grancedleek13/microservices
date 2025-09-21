package com.example.s2.service

import com.example.s2.model.CommandLogEntity
import com.example.s2.repo.CommandLogRepository
import com.example.s2.repo.MessageRepository
import com.example.s2.repo.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.client.RestTemplate
import java.time.Instant
import java.util.*

@Service
class AdminService(
    private val users: UserRepository,
    private val messages: MessageRepository,
    private val commandLogs: CommandLogRepository,
    @Value("\${app.active-window-seconds:15}") private val activeSeconds: Long,
    @Value("\${app.ms3.base-url:}") private val ms3BaseUrl: String
) {
    private val rt = RestTemplate()

    fun listUsers() = users.findAll()
    fun listActiveUsers() = users.findActiveSince(Instant.now().minusSeconds(activeSeconds))
    fun messagesByExternalId(externalId: String) = messages.findAllByUserExternalIdOrderByReceivedAtDesc(externalId)

    @Transactional
    fun deleteUser(externalId: String) {
        val user = users.findByExternalId(externalId) ?: return
        users.delete(user)
        logCommand("SYSTEM", "DELETE_USER", """{"externalId":"$externalId"}""")
        audit("DELETE_USER", externalId)
    }

    @Transactional
    fun deleteMessage(id: UUID) {
        messages.deleteById(id)
        logCommand("SYSTEM", "DELETE_MESSAGE", """{"messageId":"$id"}""")
        audit("DELETE_MESSAGE", id.toString())
    }

    private fun logCommand(source: String, action: String, payload: String) {
        commandLogs.save(CommandLogEntity(sourceServiceId = source, action = action, payload = payload, success = true))
    }

    private fun audit(action: String, subject: String) {
        if (ms3BaseUrl.isBlank()) return
        val body = mapOf("action" to action, "subject" to subject, "ts" to Instant.now().toString())
        val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
        runCatching {
            rt.postForEntity("$ms3BaseUrl/api/v1/audit", HttpEntity(body, headers), Void::class.java)
        }
    }
}
