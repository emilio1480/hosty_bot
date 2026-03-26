package com.example.hosty_bot;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
    private final ReservationService service;
    private final EmailService emailService;

    public ReservationController(ReservationService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService;
    }

    @PostMapping("/registerClient")
    public void save(@RequestBody Reservation reservation){
        Reservation res = service.save(reservation);
        String code = service.setCode(res.getId());

        String content = "Your code: " + code + "\nYour name: " + res.getName() + "\nYour room: " + reservation.getRoom();
        emailService.sendEmail(reservation.getEmail(),"Your unique code for your room", content);
    }

    @PostMapping("/getRoom")
    public void getRoom(@RequestParam String code, @RequestParam String name){
        System.out.println("ROOM NUMBER FOR: code=" + code  + " name=" + name + " is: " + service.getRoomIdByCodeAndName(code, name));
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
