plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.springframework.boot)
    alias(libs.plugins.spring.dependency.management)
    kotlin("kapt")
    `maven-publish`
}

dependencies {
    implementation(libs.kotlin.reflect)
    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.security)
    api(libs.spring.boot.starter.data.jpa)
    api(libs.jackson.module.kotlin)
    api(libs.reactor.kotlin.extensions)
    api(libs.kotlinx.coroutines.reactor)
    runtimeOnly(libs.h2)
    api(libs.firebase.admin)
    api(libs.jjwt.api)
    runtimeOnly(libs.jjwt.impl)
    runtimeOnly(libs.jjwt.jackson)
    annotationProcessor(libs.spring.boot.configuration.processor)
    kapt(libs.spring.boot.configuration.processor)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.kotlin.test.junit5)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}

java {
    withSourcesJar()
    withJavadocJar()
}

//print("GroupId: ${project.group}")
//print("Version: ${project.version}")

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = project.group.toString()
            artifactId = "notifyhub-core-starter"
            version = project.version.toString()
        }
    }
    repositories {
        mavenLocal()
    }
}