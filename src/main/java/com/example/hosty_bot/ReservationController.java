package com.example.hosty_bot;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class ReservationController {
    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String page(Model model){
        model.addAttribute("reservation", new Reservation());
        return "register";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Reservation reservation){
        Reservation res = service.save(reservation);
        service.setCode(res.getId());
        return "register";
    }
}
