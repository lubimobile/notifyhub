package io.lubimobile.notifyhub.fcm.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("notifyhub.fcm")
data class NotifyHubFcmProperties(
    var credentials: String? = null,
    var scope: String? = null,
)
