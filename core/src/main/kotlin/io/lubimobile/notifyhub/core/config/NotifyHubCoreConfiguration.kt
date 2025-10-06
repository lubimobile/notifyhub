package io.lubimobile.notifyhub.core.config

import io.lubimobile.notifyhub.core.api.UserDeviceRepository
import io.lubimobile.notifyhub.core.api.UserDeviceService
import io.lubimobile.notifyhub.core.config.properties.NotifyHubDatasourceProperties
import io.lubimobile.notifyhub.core.config.properties.NotifyHubSecurityProperties
import io.lubimobile.notifyhub.core.repository.DefaultUserDeviceRepository
import io.lubimobile.notifyhub.core.repository.H2UserDeviceRepository
import io.lubimobile.notifyhub.core.service.DefaultUserDeviceService
import io.lubimobile.notifyhub.core.web.UserDeviceController
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [
    NotifyHubDatasourceAutoConfiguration::class,
    NotifyHubJpaAutoConfiguration::class,
    NotifyHubSecurityAutoConfiguration::class,
])
@EnableConfigurationProperties(
    NotifyHubDatasourceProperties::class,
    NotifyHubSecurityProperties::class
)
class NotifyHubCoreConfiguration {

    @Bean
    @ConditionalOnMissingBean(UserDeviceRepository::class)
    fun userDeviceRepository(jpa: DefaultUserDeviceRepository): UserDeviceRepository =
        H2UserDeviceRepository(jpa)

    @Bean
    @ConditionalOnMissingBean(UserDeviceService::class)
    fun userDeviceService(repository: UserDeviceRepository): UserDeviceService =
        DefaultUserDeviceService(repository)

    @Bean
    @ConditionalOnMissingBean(UserDeviceController::class)
    fun userDeviceController(
        userDeviceService: UserDeviceService
    ): UserDeviceController = UserDeviceController(userDeviceService)
}