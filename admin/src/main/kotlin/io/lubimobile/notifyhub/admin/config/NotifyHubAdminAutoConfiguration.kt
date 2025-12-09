package io.lubimobile.notifyhub.admin.config

import io.lubimobile.notifyhub.admin.service.AdminUserDeviceService
import io.lubimobile.notifyhub.admin.web.AdminController
import io.lubimobile.notifyhub.core.api.UserDeviceRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NotifyHubAdminAutoConfiguration {

    @Bean
    fun adminUserDeviceService(
        repository: UserDeviceRepository
    ): AdminUserDeviceService = AdminUserDeviceService(repository)

    @Bean
    fun adminController(
        service: AdminUserDeviceService
    ): AdminController =
        AdminController(service)
}