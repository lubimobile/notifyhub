package io.lubimobile.notifyhub.core.dto

data class PushMessage(
    val title: String,
    val body: String,
    val data: Map<String, String> = emptyMap(),
    val image: String? = null
) {

    fun toPushNotification(token: String): PushNotification = PushNotification(
        title = title,
        body = body,
        token = token,
        image = image,
        data = data
    )
}
