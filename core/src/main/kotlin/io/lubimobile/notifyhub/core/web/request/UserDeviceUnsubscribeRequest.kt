package io.lubimobile.notifyhub.core.web.request

import com.fasterxml.jackson.annotation.JsonProperty

data class UserDeviceUnsubscribeRequest(
    @field:JsonProperty("device_token")
    val deviceToken: String
)
