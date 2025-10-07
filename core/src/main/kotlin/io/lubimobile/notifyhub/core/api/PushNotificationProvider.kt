package io.lubimobile.notifyhub.core.api

import io.lubimobile.notifyhub.core.dto.PushNotification
import io.lubimobile.notifyhub.core.constant.Platform

/**
 * Providers sending agent with
 */
interface PushNotificationProvider {

    /**
     * Platform with sending message
     */
    val platform: Platform

    /**
     * @param notification Universal notification object
     *
     * @return sending message ID
     */
    fun send(notification: PushNotification): String?
}