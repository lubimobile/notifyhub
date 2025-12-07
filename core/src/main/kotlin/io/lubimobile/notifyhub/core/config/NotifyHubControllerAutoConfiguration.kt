package io.lubimobile.notifyhub.core.config

import io.lubimobile.notifyhub.core.api.PushNotificationService
import io.lubimobile.notifyhub.core.api.UserDeviceService
import io.lubimobile.notifyhub.core.web.PushNotificationController
import io.lubimobile.notifyhub.core.web.UserDeviceController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotifyHubControllerAutoConfiguration {

    @Bean
    fun userDeviceController(
        userDeviceService: UserDeviceService
    ): UserDeviceController = UserDeviceController(userDeviceService)

    @Bean
    fun pushNotificationController(
        pushNotificationService: PushNotificationService
    ): PushNotificationController =
        PushNotificationController(pushNotificationService)
}