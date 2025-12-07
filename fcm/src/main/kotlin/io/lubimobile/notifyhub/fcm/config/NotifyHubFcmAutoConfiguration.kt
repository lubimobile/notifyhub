package io.lubimobile.notifyhub.fcm.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import io.lubimobile.notifyhub.fcm.config.properties.NotifyHubFcmProperties
import io.lubimobile.notifyhub.fcm.provider.FcmPushProvider
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource

@Configuration
class NotifyHubFcmAutoConfiguration {

    @Bean
    fun googleCredentials(properties: NotifyHubFcmProperties): GoogleCredentials {
        val path = properties.credentials

        return when {
            path.isNullOrBlank() -> GoogleCredentials.getApplicationDefault()
            path.startsWith("classpath:") -> {
                val resourcePath = path.removePrefix("classpath:")
                GoogleCredentials.fromStream(ClassPathResource(resourcePath).inputStream)
            }
            else -> GoogleCredentials.fromStream(FileSystemResource(path).inputStream)
        }
    }

    @Bean
    fun firebaseApp(credentials: GoogleCredentials): FirebaseApp {
        val firebaseOptions = FirebaseOptions.builder()
            .setCredentials(credentials)
            .build()

        return FirebaseApp.initializeApp(firebaseOptions)
    }

    @Bean
    fun firebaseMessaging(firebaseApp: FirebaseApp): FirebaseMessaging =
        FirebaseMessaging.getInstance(firebaseApp)

    @Bean
    fun fcmPushProvider(firebaseMessaging: FirebaseMessaging): FcmPushProvider =
        FcmPushProvider(firebaseMessaging)
}