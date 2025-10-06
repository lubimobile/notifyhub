package io.lubimobile.notifyhub.core.repository

import io.lubimobile.notifyhub.core.api.UserDeviceRepository
import io.lubimobile.notifyhub.core.model.UserDevice
import org.springframework.stereotype.Repository

@Repository
class H2UserDeviceRepository(
    private val jpa: DefaultUserDeviceRepository
) : UserDeviceRepository {

    override fun save(userDevice: UserDevice): UserDevice =
        jpa.save(userDevice)

    override fun findByUserId(userId: String): List<UserDevice> =
        jpa.findByUserId(userId)

    override fun findDeviceByDeviceToken(deviceToken: String): UserDevice? =
        jpa.findDeviceByDeviceToken(deviceToken)

    override fun findByUserIdAndPlatform(userId: String, platform: UserDevice.Platform): List<UserDevice> =
        jpa.findByUserIdAndPlatform(userId, platform)

    override fun findDeviceByDeviceId(deviceId: String): List<UserDevice> =
        jpa.findDeviceByDeviceId(deviceId)

    override fun deleteByDeviceToken(deviceToken: String) =
        jpa.deleteByDeviceToken(deviceToken)
}