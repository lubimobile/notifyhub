package io.lubimobile.notifyhub.core.dto

data class PushNotification(
    val title: String,
    val body: String,
    val token: String,
    val image: String? = null,
    val data: Map<String, String> = emptyMap()
)
