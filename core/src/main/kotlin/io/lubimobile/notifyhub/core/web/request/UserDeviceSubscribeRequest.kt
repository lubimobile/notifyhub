package io.lubimobile.notifyhub.core.web.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.lubimobile.notifyhub.core.model.UserDevice
import io.lubimobile.notifyhub.core.model.UserDevice.Platform

data class UserDeviceSubscribeRequest(
    @field:JsonProperty("user_id")
    val userId: String,
    @field:JsonProperty("device_token")
    val deviceToken: String,
    val platform: Platform,
    @field:JsonProperty("device_id")
    val deviceId: String
) {

    fun toModel(): UserDevice = UserDevice(
        userId = userId,
        deviceToken = deviceToken,
        platform = platform,
        deviceId = deviceId
    )
}
