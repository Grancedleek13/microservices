plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring") version "1.9.24"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
}
