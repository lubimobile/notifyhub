package io.lubimobile.notifyhub.core.repository

import io.lubimobile.notifyhub.core.api.SentMessageRepository
import io.lubimobile.notifyhub.core.model.SentMessage
import org.springframework.stereotype.Repository

@Repository
class H2SentMessageRepository(
    private val jpa: DefaultSentMessageRepository
) : SentMessageRepository {

    override fun save(sentMessage: SentMessage): SentMessage =
        jpa.save(sentMessage)

    override fun findAll(): List<SentMessage> =
        jpa.findAll()

    override fun findByUserId(userId: String): List<SentMessage> =
        jpa.findByUserId(userId)
}