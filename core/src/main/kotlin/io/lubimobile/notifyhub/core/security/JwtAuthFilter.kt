package io.lubimobile.notifyhub.core.security

import io.lubimobile.notifyhub.core.config.properties.NotifyHubSecurityProperties
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val properties: NotifyHubSecurityProperties,
    private val tokenMapper: TokenMapper
) : OncePerRequestFilter() {

    private val log = LoggerFactory.getLogger(JwtAuthFilter::class.java)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        log.info("JwtAuthFilter invoked for {}, {}", request.method, request.requestURI)
        if (request.dispatcherType.name == "ERROR") {
            log.info("Skipping JwtAuthFilter for ERROR dispatch: ${request.requestURI}")
            filterChain.doFilter(request, response)
            return
        }

        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return filterChain.doFilter(request, response)
        }

        val token = authHeader.substring(7).trim()

        log.info("Token: {}", token)

        try {
            when (token) {
                properties.staticTokens.deviceToken ->
                    setAuth("static-device", listOf(SimpleGrantedAuthority(Role.DEVICE.getRole)))
                properties.staticTokens.senderToken ->
                    setAuth("static-sender", listOf(SimpleGrantedAuthority(Role.SENDER.getRole)))
                properties.staticTokens.adminToken ->
                    setAuth("static-admin", listOf(
                        SimpleGrantedAuthority(Role.ADMIN.getRole),
                        SimpleGrantedAuthority(Role.SENDER.getRole)
                    ))
                else -> {
                    val claims = tokenMapper.getClaims(token)
                    val username = claims.subject
                    val role = claims["role"]?.toString() ?: Role.SENDER.getRole

                    setAuth(username, listOf(SimpleGrantedAuthority(role)))
                }
            }
        } catch (e: Exception) {
            log.error("Error decoding username: {}", e.message, e)
        }

        filterChain.doFilter(request, response)
    }

    private fun setAuth(username: String, roles: List<SimpleGrantedAuthority>) {
        if (SecurityContextHolder.getContext().authentication != null) return

        val auth = UsernamePasswordAuthenticationToken(username, null, roles)
        SecurityContextHolder.getContext().authentication = auth

        val roleNames = roles.joinToString { it.authority }
        log.info("Authenticated user: {} with roles: {}", username, roleNames)
    }
}