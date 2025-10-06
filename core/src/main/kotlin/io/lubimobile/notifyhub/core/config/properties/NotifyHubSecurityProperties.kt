package io.lubimobile.notifyhub.core.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "notifyhub.security")
data class NotifyHubSecurityProperties(
    var jwt: Jwt = Jwt(),
    var staticTokens: StaticTokens = StaticTokens(),
) {

    data class Jwt(
        var secret: String? = null
    )

    data class StaticTokens(
        var deviceToken: String? = null,
        var senderToken: String? = null,
        var adminToken: String? = null,
    )
}
