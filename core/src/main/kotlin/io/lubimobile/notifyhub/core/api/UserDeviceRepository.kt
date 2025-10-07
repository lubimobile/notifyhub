package io.lubimobile.notifyhub.core.api

import io.lubimobile.notifyhub.core.constant.Platform
import io.lubimobile.notifyhub.core.model.UserDevice

interface UserDeviceRepository {
    fun save(userDevice: UserDevice): UserDevice
    fun findByUserId(userId: String): List<UserDevice>
    fun findDeviceByDeviceToken(deviceToken: String): UserDevice?
    fun findByUserIdAndPlatform(userId: String, platform: Platform): List<UserDevice>
    fun findDeviceByDeviceId(deviceId: String): List<UserDevice>
    fun deleteByDeviceToken(deviceToken: String)
}