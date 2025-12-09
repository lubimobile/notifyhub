package io.lubimobile.notifyhub.admin.config

import io.lubimobile.notifyhub.admin.service.AdminService
import io.lubimobile.notifyhub.admin.web.AdminController
import io.lubimobile.notifyhub.core.api.SentMessageRepository
import io.lubimobile.notifyhub.core.api.UserDeviceRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotifyHubAdminAutoConfiguration {

    @Bean
    fun adminUserDeviceService(
        userDeviceRepository: UserDeviceRepository,
        sentMessageRepository: SentMessageRepository
    ): AdminService = AdminService(userDeviceRepository, sentMessageRepository)

    @Bean
    fun adminController(
        service: AdminService
    ): AdminController = AdminController(service)
}