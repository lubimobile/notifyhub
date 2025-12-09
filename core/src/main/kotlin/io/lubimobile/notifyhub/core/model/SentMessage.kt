package io.lubimobile.notifyhub.core.model

import io.lubimobile.notifyhub.core.dto.PushNotificationResult
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "sent_message")
data class SentMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "user_id", nullable = false)
    val userId: String? = null,

    @Embedded
    @Column(nullable = false)
    val result: PushNotificationResult? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant = Instant.now()
)