package io.lubimobile.notifyhub.core.api

import io.lubimobile.notifyhub.core.dto.PushNotificationResult

interface SentMessageService {

    fun savePushResult (userId: String, result: PushNotificationResult)
}