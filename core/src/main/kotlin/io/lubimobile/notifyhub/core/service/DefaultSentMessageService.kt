package io.lubimobile.notifyhub.core.service

import io.lubimobile.notifyhub.core.api.SentMessageRepository
import io.lubimobile.notifyhub.core.api.SentMessageService
import io.lubimobile.notifyhub.core.dto.PushNotificationResult
import io.lubimobile.notifyhub.core.model.SentMessage
import org.springframework.stereotype.Service

@Service
class DefaultSentMessageService(
    private val repository: SentMessageRepository
) : SentMessageService {

    override fun savePushResult (userId: String, result: PushNotificationResult) {
        val sentMessage = SentMessage(
            userId = userId,
            result = result,
        )
        repository.save(sentMessage)
    }
}