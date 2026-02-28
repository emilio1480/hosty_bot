package com.example.hosty_bot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final ReservationService service;

    public RestController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("/getRoom")
    public Integer getRoom(@RequestParam String code, @RequestParam String name){
        return service.getRoomIdByCodeAndName(code, name);
    }
}
