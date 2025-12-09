package io.lubimobile.notifyhub.core.api

import io.lubimobile.notifyhub.core.model.SentMessage

interface SentMessageRepository {
    fun save(sentMessage: SentMessage): SentMessage
    fun findAll(): List<SentMessage>
    fun findByUserId(userId: String): List<SentMessage>
}