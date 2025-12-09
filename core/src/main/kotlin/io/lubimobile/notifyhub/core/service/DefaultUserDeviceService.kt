package io.lubimobile.notifyhub.core.service

import io.lubimobile.notifyhub.core.api.UserDeviceRepository
import io.lubimobile.notifyhub.core.api.UserDeviceService
import io.lubimobile.notifyhub.core.model.UserDevice
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DefaultUserDeviceService(
    private val repository: UserDeviceRepository
) : UserDeviceService {

    override fun saveUserDevice(userDevice: UserDevice) {
        val existingDevice = repository.findDeviceByDeviceToken(userDevice.deviceToken)
        if (existingDevice != null) {
            repository.save(existingDevice.copy(userId = userDevice.userId, deviceId = userDevice.userId))
        } else {
            repository.save(userDevice)
        }
    }

    override fun deleteUserDevice(deviceToken: String) {
        repository.deleteByDeviceToken(deviceToken)
    }

    override fun findByUserId(userId: String): List<UserDevice> = repository.findByUserId(userId)

    @Transactional
    override fun deleteInvalidToken(deviceToken: String) = repository.deleteByDeviceToken(deviceToken)

    @Transactional
    override fun deleteInvalidToken(userId: String, deviceToken: String) =
        repository.deleteByUserIdAndDeviceToken(userId, deviceToken)
}