package io.lubimobile.notifyhub.core.repository

import io.lubimobile.notifyhub.core.model.SentMessage
import org.springframework.data.jpa.repository.JpaRepository

interface DefaultSentMessageRepository : JpaRepository<SentMessage, Int> {

    fun findByUserId(userId: String): List<SentMessage>
}