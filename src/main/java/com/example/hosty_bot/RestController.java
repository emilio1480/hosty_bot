package com.example.hosty_bot;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final ReservationService service;

    public RestController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("/getRoomNumber")
    public Integer getRoomNumber(String code, String fullName){
        return service.getRoomIdByCodeAndName(code, fullName);
    }
}
