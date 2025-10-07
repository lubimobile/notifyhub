package io.lubimobile.notifyhub.core.web.response

import org.slf4j.Logger
import io.lubimobile.notifyhub.core.dto.PushNotificationResult
import io.lubimobile.notifyhub.core.constant.Platform

data class PushNotificationFromUserResponse(
    val results: List<PushNotificationResult>
) {

    val countWithSuccess: Map<Platform, Int> = results
        .filter { it.success }
        .groupingBy { it.platform }
        .eachCount()

    val countWithError: Map<Platform, Int> = results
        .filter { !it.success }
        .groupingBy { it.platform }
        .eachCount()

    fun logging(logger: Logger) {
        logger.debug("""
            |Push notifications
            |successful: {} 
            |and with errors: {}
        """.trimMargin(), this.countWithSuccess, this.countWithError)
    }
}