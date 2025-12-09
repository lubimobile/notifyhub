package io.lubimobile.notifyhub.core.config

import io.lubimobile.notifyhub.core.api.SentMessageRepository
import io.lubimobile.notifyhub.core.api.UserDeviceRepository
import io.lubimobile.notifyhub.core.repository.DefaultSentMessageRepository
import io.lubimobile.notifyhub.core.repository.DefaultUserDeviceRepository
import io.lubimobile.notifyhub.core.repository.H2SentMessageRepository
import io.lubimobile.notifyhub.core.repository.H2UserDeviceRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotifyHubRepositoryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(UserDeviceRepository::class)
    fun userDeviceRepository(jpa: DefaultUserDeviceRepository): UserDeviceRepository =
        H2UserDeviceRepository(jpa)

    @Bean
    @ConditionalOnMissingBean(SentMessageRepository::class)
    fun sentMessageRepository(jpa: DefaultSentMessageRepository): SentMessageRepository =
        H2SentMessageRepository(jpa)
}