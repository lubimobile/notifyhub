package io.lubimobile.notifyhub.admin.service

import io.lubimobile.notifyhub.core.api.UserDeviceRepository
import io.lubimobile.notifyhub.core.model.UserDevice
import org.springframework.stereotype.Service

@Service
class AdminUserDeviceService(
    private val repository: UserDeviceRepository
) {

    fun allUserDevices(): List<UserDevice> =
        repository.findAll()
}