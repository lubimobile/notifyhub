package io.lubimobile.notifyhub.core.web.request

import com.fasterxml.jackson.annotation.JsonProperty
import io.lubimobile.notifyhub.core.dto.PushMessage

data class PushNotificationFromUserRequest(
    @field:JsonProperty("user_id")
    val userId: String,
    val message: PushMessage
)
