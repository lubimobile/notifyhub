package io.lubimobile.notifyhub.core.api

import io.lubimobile.notifyhub.core.model.UserDevice

interface UserDeviceService {

    fun saveUserDevice(userDevice: UserDevice)

    fun findByUserId(userId: String): List<UserDevice>

    fun deleteInvalidToken(deviceToken: String)

    fun deleteInvalidToken(userId: String, deviceToken: String)
}