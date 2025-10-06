package io.lubimobile.notifyhub.core.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.lubimobile.notifyhub.core.config.properties.NotifyHubSecurityProperties
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.Date

@Component
class TokenMapper(
    private val properties: NotifyHubSecurityProperties
) {

    private val secret = properties.jwt.secret ?: ""

    fun getClaims(token: String): Claims = Jwts.parser()
        .verifyWith(Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8)))
        .build()
        .parseSignedClaims(token)
        .payload

    fun createToken(username: String): String = Jwts.builder()
        .subject(username)
        .claim("role", getRole(username))
        .issuedAt(Date())
        .expiration(Date(System.currentTimeMillis() + 1_000 * 60 * 60))
        .signWith(Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8)))
        .compact()


    private fun getRole(username: String): String = when (username) {
        "admin" -> Role.ADMIN.getRole
        "sender" -> Role.SENDER.getRole
        else -> throw IllegalArgumentException("Unknown username $username")
    }
}