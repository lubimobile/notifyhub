package io.lubimobile.notifyhub.core.service

import io.lubimobile.notifyhub.core.api.PushNotificationManager
import io.lubimobile.notifyhub.core.api.PushNotificationService
import io.lubimobile.notifyhub.core.api.UserDeviceService
import io.lubimobile.notifyhub.core.dto.PushMessage
import io.lubimobile.notifyhub.core.dto.PushNotificationResult
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DefaultPushNotificationService(
    private val pushNotificationManager: PushNotificationManager,
    private val userDeviceService: UserDeviceService
) : PushNotificationService {

    private val logger = LoggerFactory.getLogger(DefaultPushNotificationService::class.java)

    override fun sendFromAllUserDevices(userId: String, pushMessage: PushMessage): List<PushNotificationResult> = userDeviceService.findByUserId(userId).map { userDevice ->
        try {
            val messageId: String? = pushNotificationManager.send(
                userDevice.platform,
                pushMessage.toPushNotification(userDevice.deviceToken)
            )
            logger.debug("Successfully sent push notification from user: {} to {}", userId, userDevice.deviceToken)
            return@map PushNotificationResult(
                success = true,
                platform = userDevice.platform,
                token = userDevice.deviceToken,
                messageId = messageId
            )
        } catch (e: Exception) {
            logger.warn("Fails to send push notification from user: {}, on device: {}", userId, userDevice.deviceId, e)
            PushNotificationResult(
                success = false,
                platform = userDevice.platform,
                token = userDevice.deviceToken,
                error = e.message
            )
        }
    }
}