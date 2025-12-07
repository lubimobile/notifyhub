package io.lubimobile.notifyhub.core.config

import io.lubimobile.notifyhub.core.api.PushNotificationManager
import io.lubimobile.notifyhub.core.api.PushNotificationProvider
import io.lubimobile.notifyhub.core.api.PushNotificationService
import io.lubimobile.notifyhub.core.api.UserDeviceRepository
import io.lubimobile.notifyhub.core.api.UserDeviceService
import io.lubimobile.notifyhub.core.manager.DefaultPushNotificationManager
import io.lubimobile.notifyhub.core.constant.Platform
import io.lubimobile.notifyhub.core.service.DefaultPushNotificationService
import io.lubimobile.notifyhub.core.service.DefaultUserDeviceService
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotifyHubServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(UserDeviceService::class)
    fun userDeviceService(repository: UserDeviceRepository): UserDeviceService =
        DefaultUserDeviceService(repository)

    @Bean
    @ConditionalOnMissingBean(PushNotificationManager::class)
    fun pushNotificationManager(
        providers: List<PushNotificationProvider>
    ): PushNotificationManager {
        val providersMap: Map<Platform, PushNotificationProvider> = providers.associateBy { it.platform }
        return DefaultPushNotificationManager(providersMap)
    }

    @Bean
    @ConditionalOnMissingBean(PushNotificationService::class)
    fun pushNotificationService(
        pushNotificationManager: PushNotificationManager,
        userDeviceService: UserDeviceService,
    ): PushNotificationService =
        DefaultPushNotificationService(pushNotificationManager, userDeviceService)
}