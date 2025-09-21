plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring") version "1.9.24"
    kotlin("plugin.jpa") version "1.9.24"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.postgresql:postgresql:42.7.3")
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}
