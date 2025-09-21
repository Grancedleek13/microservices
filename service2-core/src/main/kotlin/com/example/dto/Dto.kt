package com.example.dto

import java.time.Instant

enum class ServiceType { TYPE1, TYPE3 }
enum class Status { ACTIVE, INACTIVE }

data class StatusMessage(
    val serviceId: String,
    val serviceType: ServiceType,
    val status: Status,
    val timestamp: Instant = Instant.now()
)

data class DataMessage(
    val serviceId: String,
    val serviceType: ServiceType = ServiceType.TYPE1,
    val text: String,
    val timestamp: Instant = Instant.now()
)
