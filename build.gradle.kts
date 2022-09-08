val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.10"
    id("io.ktor.plugin") version "2.1.1"
}

group = "net.cheszx"
version = "0.0.1"
application {
    mainClass.set("net.cheszx.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("commons-codec:commons-codec:1.15")
    implementation("org.jetbrains.exposed:exposed-core:0.39.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.39.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.39.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.39.2")
    implementation("org.postgresql:postgresql:42.5.0")
    implementation("com.zaxxer:HikariCP:5.0.1")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}