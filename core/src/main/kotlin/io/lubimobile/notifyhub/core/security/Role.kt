package io.lubimobile.notifyhub.core.security

enum class Role {
    ADMIN, SENDER, DEVICE;

    val getRole: String = "ROLE_${this.name}"
}