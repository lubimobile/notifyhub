package io.lubimobile.notifyhub.core.web

import io.lubimobile.notifyhub.core.api.PushNotificationService
import io.lubimobile.notifyhub.core.web.request.PushNotificationFromUserRequest
import io.lubimobile.notifyhub.core.web.response.PushNotificationFromUserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/push-notification")
class PushNotificationController(
    private val pushNotificationService: PushNotificationService
) {

    @PostMapping("/from-user")
    fun pushNotificationFromUser(@RequestBody request: PushNotificationFromUserRequest): ResponseEntity<PushNotificationFromUserResponse> {
            val pushNotificationResults =
                pushNotificationService.sendFromAllUserDevices(request.userId, request.message)

        val pushNotificationFromUserResponse = PushNotificationFromUserResponse(pushNotificationResults)

        return ResponseEntity.ok(pushNotificationFromUserResponse)
    }
}