package io.lubimobile.notifyhub.core.config

import io.lubimobile.notifyhub.core.config.properties.NotifyHubDatasourceProperties
import io.lubimobile.notifyhub.core.config.properties.NotifyHubSecurityProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [
    NotifyHubDatasourceAutoConfiguration::class,
    NotifyHubJpaAutoConfiguration::class,
    NotifyHubSecurityAutoConfiguration::class,
    NotifyHubServiceAutoConfiguration::class,
    NotifyHubRepositoryAutoConfiguration::class,
    NotifyHubControllerAutoConfiguration::class
])
@EnableConfigurationProperties(
    NotifyHubDatasourceProperties::class,
    NotifyHubSecurityProperties::class
)
class NotifyHubCoreStarter