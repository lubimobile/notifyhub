package io.lubimobile.notifyhub.core.dto

import io.lubimobile.notifyhub.core.constant.Platform
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
data class PushNotificationResult(
    val success: Boolean = true,
    val token: String? = null,
    @Enumerated(EnumType.STRING)
    val platform: Platform = Platform.UNKNOWN,
    val messageId: String? = null,
    val error: String? = null
)
