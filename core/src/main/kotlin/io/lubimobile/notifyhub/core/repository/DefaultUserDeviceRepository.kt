package io.lubimobile.notifyhub.core.repository

import io.lubimobile.notifyhub.core.constant.Platform
import io.lubimobile.notifyhub.core.model.UserDevice
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface DefaultUserDeviceRepository : JpaRepository<UserDevice, UUID> {
    fun findByUserId(userId: String): List<UserDevice>
    fun findDeviceByDeviceToken(deviceToken: String): UserDevice?
    fun findByUserIdAndPlatform(userId: String, platform: Platform): List<UserDevice>
    fun findDeviceByDeviceId(deviceId: String): List<UserDevice>
    fun deleteByDeviceToken(deviceToken: String)
}