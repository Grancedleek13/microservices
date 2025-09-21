<<<<<<< HEAD
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.24" apply false
    id("org.springframework.boot") version "2.7.18" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
}

allprojects {
    group = "com.example"
    version = "1.0.0"
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")

    // Общие зависимости для всех подпроектов
    dependencies {
        "implementation"(kotlin("stdlib"))
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}
=======
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.24" apply false
    id("org.springframework.boot") version "2.7.18" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
}

allprojects {
    group = "com.example"
    version = "1.0.0"
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "io.spring.dependency-management")

    // Общие зависимости для всех подпроектов
    dependencies {
        "implementation"(kotlin("stdlib"))
        "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.2")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}
>>>>>>> ef3d020 (Initial commit: microservices demo project)
