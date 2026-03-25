package com.example.hosty_bot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final ReservationService service;

    public RestController(ReservationService service) {
        this.service = service;
    }

    @PostMapping("/getRoomByCodeAndNumber")
    public Integer getRoomByCodeAndNumber(@RequestBody UserInformation userInfo){
        return service.getRoomIdByCodeAndName(userInfo.code(), userInfo.name());
    }

    @PostMapping("/getRoomByCode")
    public Integer getRoomByCode(@RequestBody UserInformation userInfo){
        return service.getRoomIdByCode(userInfo.code());
    }
}
