package io.lubimobile.notifyhub.core.web

import io.lubimobile.notifyhub.core.api.UserDeviceService
import io.lubimobile.notifyhub.core.web.request.UserDeviceSubscribeRequest
import io.lubimobile.notifyhub.core.web.request.UserDeviceUnsubscribeRequest
import io.lubimobile.notifyhub.core.web.response.UserDeviceResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user-device")
class UserDeviceController(
    private val userDeviceService: UserDeviceService
) {

    companion object {
        const val SUBSCRIBES = "SUBSCRIBES"
        const val UNSUBSCRIBE = "UNSUBSCRIBE"
        const val FAILURE = "FAILURE"
    }

    private val log: Logger = LoggerFactory.getLogger(UserDeviceController::class.java)

    @PostMapping("/subscribe")
    fun subscribe(@RequestBody request: UserDeviceSubscribeRequest): ResponseEntity<UserDeviceResponse> {
        val userDevice = request.toModel()
        userDeviceService.saveUserDevice(userDevice)

        log.debug("Subscribe User Device with userId: '{}' completed successfully", request.userId)

        return ResponseEntity.ok(UserDeviceResponse(SUBSCRIBES))
    }

    @PostMapping("/unsubscribe")
    fun unsubscribe(@RequestBody request: UserDeviceUnsubscribeRequest): ResponseEntity<UserDeviceResponse> {
        userDeviceService.deleteInvalidToken(request.deviceToken)

        return ResponseEntity.ok(UserDeviceResponse(UNSUBSCRIBE))
    }

    @ExceptionHandler(Throwable::class)
    fun handleException(ex: Throwable): ResponseEntity<UserDeviceResponse> {
        log.warn(ex.message, ex)
        return ResponseEntity.internalServerError()
            .body(UserDeviceResponse(FAILURE, ex.message))
    }
}