package io.lubimobile.notifyhub.admin.service

import io.lubimobile.notifyhub.core.api.SentMessageRepository
import io.lubimobile.notifyhub.core.api.UserDeviceRepository
import io.lubimobile.notifyhub.core.model.SentMessage
import io.lubimobile.notifyhub.core.model.UserDevice
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val userDeviceRepository: UserDeviceRepository,
    private val sentMessageRepository: SentMessageRepository
) {

    fun allUserDevices(): List<UserDevice> =
        userDeviceRepository.findAll()

    fun allSentMessages(): List<SentMessage> =
        sentMessageRepository.findAll()

    fun findSentMessagesWithUser(userId: String): List<SentMessage> =
        sentMessageRepository.findByUserId(userId)

    fun sendingMessage(userId: String? = null): List<SentMessage> =
        userId?.let { findSentMessagesWithUser(it) } ?: allSentMessages()
}