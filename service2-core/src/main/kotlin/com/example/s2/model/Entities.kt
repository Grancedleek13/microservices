package com.example.s2.model

import com.example.dto.Status
import com.example.dto.ServiceType
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity @Table(name = "users")
class UserEntity(
    @Id @GeneratedValue var id: UUID? = null,
    @Column(nullable = false, unique = true) var externalId: String,
    @Enumerated(EnumType.STRING) @Column(nullable = false) var serviceType: ServiceType,
    @Enumerated(EnumType.STRING) @Column(nullable = false) var lastStatus: Status = Status.ACTIVE,
    @Column(nullable = false) var lastSeenAt: Instant = Instant.now(),
    @Column(nullable = false) var createdAt: Instant = Instant.now()
)

@Entity @Table(name = "messages")
class MessageEntity(
    @Id @GeneratedValue var id: UUID? = null,
    @ManyToOne(optional = false) @JoinColumn(name = "user_id") var user: UserEntity,
    @Column(nullable = false, columnDefinition = "text") var text: String,
    @Column(nullable = false) var receivedAt: Instant = Instant.now()
)

@Entity @Table(name = "activities")
class ActivityEntity(
    @Id @GeneratedValue var id: UUID? = null,
    @ManyToOne(optional = false) @JoinColumn(name = "user_id") var user: UserEntity,
    @Enumerated(EnumType.STRING) @Column(nullable = false) var status: Status,
    @Column(nullable = false) var occurredAt: Instant = Instant.now()
)

@Entity @Table(name = "command_logs")
class CommandLogEntity(
    @Id @GeneratedValue var id: UUID? = null,
    @Column(nullable = false) var sourceServiceId: String,
    @Column(nullable = false) var action: String,
    @Column(nullable = false, columnDefinition = "text") var payload: String,
    @Column(nullable = false) var success: Boolean = true,
    @Column(nullable = false) var createdAt: Instant = Instant.now()
)
