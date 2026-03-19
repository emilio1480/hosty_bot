package com.example.hosty_bot;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class ReservationController {
    private final ReservationService service;
    private final EmailService emailService;

    public ReservationController(ReservationService service, EmailService emailService) {
        this.service = service;
        this.emailService = emailService;
    }

    @GetMapping("/register")
    public String page(Model model){
        model.addAttribute("reservation", new Reservation());
        return "register";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Reservation reservation){
        reservation.setName(reservation.getName().trim().toLowerCase());
        Reservation res = service.save(reservation);
        String code = service.setCode(res.getId());

        String content = "Your code: " + code + " your name: " + res.getName();
        emailService.sendEmail(reservation.getEmail(),"Your unique code for your room", content);
        return "redirect:/room.html";
    }

    @PostMapping("/getRoom")
    public String getRoom(@RequestParam String code, @RequestParam String name){
        System.out.println("ROOM NUMBER FOR: code=" + code  + " name=" + name + " is: " + service.getRoomIdByCodeAndName(code, name));
        return "redirect:/room.html";
    }
}
