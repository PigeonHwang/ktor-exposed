val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

val kgraphql_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.10"
    id("io.ktor.plugin") version "2.1.0"
    kotlin("plugin.serialization").version("1.7.10")
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")

    implementation("org.flywaydb:flyway-core:9.1.6")
    implementation("org.postgresql:postgresql:42.4.2")
    implementation("com.zaxxer:HikariCP:5.0.1")

    /** exposed */
    implementation("org.jetbrains.exposed:exposed-core:0.35.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.35.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.39.2")

    /** json for ktor */
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    /** Gson */
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")

    /** additional types for exposed */
    implementation("org.jetbrains.exposed:exposed-java-time:0.35.2")

    /** kgraphql */
    //implementation("com.apurebase:kgraphql:$kgraphql_version")
    //implementation("com.apurebase:kgraphql-ktor:$kgraphql_version")
    /** graphql-kotlin */
    //implementation("com.expediagroup", "graphql-kotlin-server", "6.2.1")
}