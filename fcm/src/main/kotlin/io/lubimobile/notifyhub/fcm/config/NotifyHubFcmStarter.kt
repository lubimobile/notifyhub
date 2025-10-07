package io.lubimobile.notifyhub.fcm.config

import io.lubimobile.notifyhub.fcm.config.properties.NotifyHubFcmProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [
    NotifyHubFcmAutoConfiguration::class,
])
@EnableConfigurationProperties(
    NotifyHubFcmProperties::class
)
class NotifyHubFcmStarter