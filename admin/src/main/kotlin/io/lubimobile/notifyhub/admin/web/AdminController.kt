package io.lubimobile.notifyhub.admin.web

import io.lubimobile.notifyhub.admin.service.AdminUserDeviceService
import io.lubimobile.notifyhub.core.model.UserDevice
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api/admin"])
class AdminController(
    private val service: AdminUserDeviceService
) {

    @GetMapping("user-devices")
    fun allSubscribeUserDevices(): ResponseEntity<List<UserDevice>> =
        ResponseEntity.ok(service.allUserDevices())
}