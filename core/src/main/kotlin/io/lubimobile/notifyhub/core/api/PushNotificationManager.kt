package io.lubimobile.notifyhub.core.api

import io.lubimobile.notifyhub.core.dto.PushNotification
import io.lubimobile.notifyhub.core.constant.Platform

interface PushNotificationManager {
    fun send(platform: Platform, notification: PushNotification): String?
}