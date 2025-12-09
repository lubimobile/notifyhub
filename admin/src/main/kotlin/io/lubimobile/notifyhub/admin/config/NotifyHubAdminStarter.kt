package io.lubimobile.notifyhub.admin.config

import io.lubimobile.notifyhub.core.repository.DefaultSentMessageRepository
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(value = [
    DefaultSentMessageRepository::class
])
class NotifyHubAdminStarter