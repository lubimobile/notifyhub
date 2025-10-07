plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.springframework.boot)
    alias(libs.plugins.spring.dependency.management)
    kotlin("kapt")
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(libs.spring.boot.starter.web)
    api(libs.firebase.admin)

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

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = project.group.toString()
            artifactId = "notifyhub-fcm-starter"
            version = project.version.toString()
        }
    }
    repositories {
        mavenLocal()
    }
}