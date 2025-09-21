package com.example.s3.web

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/api/v1")
class AdminController(
    private val wc: WebClient,
    @Value("\${app.ms2.base-url:http://localhost:8082}") private val baseUrl: String
) {

    @GetMapping("/users")
    fun users(): Mono<Any> = wc.get().uri("$baseUrl/internal/users").retrieve().bodyToMono(Any::class.java)

    @GetMapping("/users/active")
    fun active(): Mono<Any> = wc.get().uri("$baseUrl/internal/users/active").retrieve().bodyToMono(Any::class.java)

    @GetMapping("/users/{externalId}/messages")
    fun userMessages(@PathVariable externalId: String): Mono<Any> =
        wc.get().uri("$baseUrl/internal/users/{id}/messages", externalId).retrieve().bodyToMono(Any::class.java)

    @DeleteMapping("/users/{externalId}")
    fun deleteUser(@PathVariable externalId: String): Mono<Any> =
        wc.delete().uri("$baseUrl/internal/users/{id}", externalId).retrieve().bodyToMono(Any::class.java)

    @DeleteMapping("/messages/{id}")
    fun deleteMessage(@PathVariable id: UUID): Mono<Any> =
        wc.delete().uri("$baseUrl/internal/messages/{id}", id).retrieve().bodyToMono(Any::class.java)

    data class AuditReq(val action: String, val subject: String, val ts: String)
    @PostMapping("/audit", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun audit(@RequestBody req: AuditReq): Map<String, String> {
        println("AUDIT from MS2: $req")
        return mapOf("status" to "ok")
    }
}
