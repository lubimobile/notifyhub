package io.lubimobile.notifyhub.core.manager

import io.lubimobile.notifyhub.core.api.PushNotificationManager
import io.lubimobile.notifyhub.core.api.PushNotificationProvider
import io.lubimobile.notifyhub.core.dto.PushNotification
import io.lubimobile.notifyhub.core.constant.Platform

class DefaultPushNotificationManager(
    private val providers: Map<Platform, PushNotificationProvider>
) : PushNotificationManager {

    override fun send(platform: Platform, notification: PushNotification): String? {
        val provider = providers[platform] ?: error("No provider for platform $platform registered")
        return provider.send(notification)
    }
}