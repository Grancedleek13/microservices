package com.example.s2.repo

import com.example.s2.model.*
import com.example.dto.ServiceType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.Instant
import java.util.*

interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByExternalId(externalId: String): UserEntity?
    @Query("select u from UserEntity u where u.lastSeenAt >= :since")
    fun findActiveSince(since: Instant): List<UserEntity>
    fun findByServiceType(type: ServiceType): List<UserEntity>
}

interface MessageRepository : JpaRepository<MessageEntity, UUID> {
    fun findAllByUserExternalIdOrderByReceivedAtDesc(externalId: String): List<MessageEntity>
}

interface ActivityRepository : JpaRepository<ActivityEntity, UUID>
interface CommandLogRepository : JpaRepository<CommandLogEntity, UUID>
