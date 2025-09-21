package com.example.s2.web

import com.example.s2.service.AdminService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/internal")
class InternalController(private val admin: AdminService) {

    @GetMapping("/users") fun users() = admin.listUsers()
    @GetMapping("/users/active") fun active() = admin.listActiveUsers()
    @GetMapping("/users/{externalId}/messages") fun messages(@PathVariable externalId: String) =
        admin.messagesByExternalId(externalId)

    @DeleteMapping("/users/{externalId}") fun deleteUser(@PathVariable externalId: String) =
        mapOf("deleted" to (runCatching { admin.deleteUser(externalId) }.isSuccess))

    @DeleteMapping("/messages/{id}") fun deleteMessage(@PathVariable id: UUID) =
        mapOf("deleted" to (runCatching { admin.deleteMessage(id) }.isSuccess))
}
