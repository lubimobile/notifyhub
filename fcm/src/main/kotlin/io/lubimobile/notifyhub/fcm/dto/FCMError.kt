package io.lubimobile.notifyhub.fcm.dto

data class FCMError(
    val code: Int,
    val message: String,
    val status: String,
    val details: List<FCMErrorDetail> = emptyList()
)
