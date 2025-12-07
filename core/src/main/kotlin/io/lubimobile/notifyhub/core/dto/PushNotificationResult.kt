package io.lubimobile.notifyhub.core.dto

import io.lubimobile.notifyhub.core.constant.Platform

data class PushNotificationResult(
    val success: Boolean,
    val token: String,
    val platform: Platform,
    val messageId: String? = null,
    val error: String? = null
)
