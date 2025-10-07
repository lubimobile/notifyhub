package io.lubimobile.notifyhub.core.model

import io.lubimobile.notifyhub.core.constant.Platform
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "user_devices")
data class UserDevice(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "user_id", nullable = false)
    val userId: String = "",

    @Column(name = "device_token", nullable = false, unique = true)
    val deviceToken: String = "",

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val platform: Platform = Platform.RUSTORE,

    @Column(name = "device_id", nullable = false)
    val deviceId: String = "",

    @Column(name = "created_at", nullable = false)
    val createdAt: Instant = Instant.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()
) {

    @PreUpdate
    fun onPreUpdate() {
        updatedAt = Instant.now()
    }
}