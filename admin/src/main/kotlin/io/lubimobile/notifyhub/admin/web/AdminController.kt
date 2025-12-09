package io.lubimobile.notifyhub.admin.web

import io.lubimobile.notifyhub.admin.service.AdminService
import io.lubimobile.notifyhub.core.model.SentMessage
import io.lubimobile.notifyhub.core.model.UserDevice
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api/admin"])
class AdminController(
    private val service: AdminService
) {

    @GetMapping("user-devices")
    fun allSubscribeUserDevices(): ResponseEntity<List<UserDevice>> =
        ResponseEntity.ok(service.allUserDevices())

    @GetMapping("sent-message")
    fun sendMessage(@RequestParam(required = false) userId: String? = null): ResponseEntity<List<SentMessage>> =
        ResponseEntity.ok(service.sendingMessage(userId))
}