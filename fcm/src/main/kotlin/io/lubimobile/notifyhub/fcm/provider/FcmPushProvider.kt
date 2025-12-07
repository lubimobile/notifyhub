package io.lubimobile.notifyhub.fcm.provider

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import io.lubimobile.notifyhub.core.api.PushNotificationProvider
import io.lubimobile.notifyhub.core.dto.PushNotification
import io.lubimobile.notifyhub.core.constant.Platform
import io.lubimobile.notifyhub.core.constant.Platform.FCM
import org.springframework.stereotype.Component

@Component
class FcmPushProvider(
    private val firebaseMessaging: FirebaseMessaging
) : PushNotificationProvider {

    override val platform: Platform = FCM

    override fun send(notification: PushNotification): String? {
        val fcmNotification = Notification.builder()
            .setTitle(notification.title)
            .setBody(notification.body)
            .setImage(notification.image)
            .build()

        val message = Message.builder()
            .setToken(notification.token)
            .putAllData(notification.data)
            .setNotification(fcmNotification)
            .build()
        return firebaseMessaging.send(message)
    }
}