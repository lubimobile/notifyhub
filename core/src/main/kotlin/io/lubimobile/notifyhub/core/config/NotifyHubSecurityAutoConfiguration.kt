package io.lubimobile.notifyhub.core.config

import io.lubimobile.notifyhub.core.config.properties.NotifyHubSecurityProperties
import io.lubimobile.notifyhub.core.security.JwtAuthFilter
import io.lubimobile.notifyhub.core.security.Role
import io.lubimobile.notifyhub.core.security.TokenMapper
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class NotifyHubSecurityAutoConfiguration(
    private val properties: NotifyHubSecurityProperties
) {

    private val log = LoggerFactory.getLogger(NotifyHubSecurityAutoConfiguration::class.java)

    @Bean
    @ConditionalOnMissingBean(TokenMapper::class)
    fun tokenMapper(): TokenMapper =
        TokenMapper(properties)

    @Bean
    fun jwtAuthFilter(
        tokenMapper: TokenMapper
    ): JwtAuthFilter =
        JwtAuthFilter(properties, tokenMapper)

    @Bean
    fun filterChain(http: HttpSecurity, jwtAuthFilter: JwtAuthFilter): SecurityFilterChain = http
        .csrf { it.disable() }
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .authorizeHttpRequests {
            it.requestMatchers("/error").permitAll()
            it.requestMatchers("/auth/**").permitAll()
            it.requestMatchers("/api/send/**").hasRole(Role.SENDER.name)
            it.requestMatchers("/api/user-device/**").hasRole(Role.DEVICE.name)
            it.requestMatchers("/api/admin/**").hasRole(Role.ADMIN.name)
            it.anyRequest().permitAll()
        }
        .exceptionHandling {
            it.authenticationEntryPoint { request, response, authException ->
                log.warn(
                    """
                    🚫 UNAUTHORIZED REQUEST
                    Path: ${request.requestURI}
                    Method: ${request.method}
                    Reason: ${authException.message}
                    """.trimIndent()
                )
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
            }
            it.accessDeniedHandler(loggerAccessDinedHandler())
        }
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
        .build()

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager

    @Bean
    fun loggerAccessDinedHandler(): AccessDeniedHandler =  AccessDeniedHandler { request, response, accessDeniedException ->
        val auth: Authentication? = SecurityContextHolder.getContext().authentication
        val user = auth?.name ?: "AnonymousUser"
        val authorities = auth?.authorities?.joinToString { it.authority } ?: "none"

        log.warn(
            """
            🚫 ACCESS DENIED
            User: $user
            Roles: $authorities
            Path: ${request.requestURI}
            Method: ${request.method}
            Reason: ${accessDeniedException.message}
            """.trimIndent()
        )

        response.status = HttpServletResponse.SC_FORBIDDEN
        response.writer.write("Forbidden: ${accessDeniedException.message}")
    }
}