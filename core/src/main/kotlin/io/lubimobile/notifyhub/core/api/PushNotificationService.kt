package io.lubimobile.notifyhub.core.api

import io.lubimobile.notifyhub.core.dto.PushMessage
import io.lubimobile.notifyhub.core.dto.PushNotificationResult

interface PushNotificationService {
    fun sendFromAllUserDevices(userId: String, pushMessage: PushMessage): List<PushNotificationResult>
}