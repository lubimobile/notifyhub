package io.lubimobile.notifyhub.core.service

import io.lubimobile.notifyhub.core.api.UserDeviceRepository
import io.lubimobile.notifyhub.core.api.UserDeviceService
import io.lubimobile.notifyhub.core.model.UserDevice
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.time.Instant

@Service
class DefaultUserDeviceService(
    private val repository: UserDeviceRepository
) : UserDeviceService {

    private val log = LoggerFactory.getLogger(DefaultUserDeviceService::class.java)

    @Transactional
    override fun saveUserDevice(userDevice: UserDevice) {
        val existingDevice = repository.findDeviceByDeviceToken(userDevice.deviceToken)
        log.debug("Saving user device {}", existingDevice)
        if (existingDevice != null) {
            val updateDevice = existingDevice.copy(
                userId = userDevice.userId,
                deviceId = userDevice.deviceId,
            )
            log.debug("Saving user update device data {}", updateDevice)
            repository.save(updateDevice)
        } else {
            repository.save(userDevice)
        }
    }

    override fun findByUserId(userId: String): List<UserDevice> =
        repository.findByUserId(userId)

    @Transactional
    override fun deleteInvalidToken(deviceToken: String) =
        repository.deleteByDeviceToken(deviceToken)

    @Transactional
    override fun deleteInvalidToken(userId: String, deviceToken: String) =
        repository.deleteByUserIdAndDeviceToken(userId, deviceToken)
}