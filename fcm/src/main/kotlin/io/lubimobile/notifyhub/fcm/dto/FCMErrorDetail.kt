package io.lubimobile.notifyhub.fcm.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class FCMErrorDetail(
    @field:JsonProperty("@type")
    val type: String,
    val errorCode: String? = null
)
