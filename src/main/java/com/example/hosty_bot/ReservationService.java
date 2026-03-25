package com.example.hosty_bot;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ReservationService {
    private final ReservationRepository repository;
    private final Random random = new Random();

    public ReservationService(ReservationRepository reservationRepository) {
        repository = reservationRepository;
    }

    public Reservation save(Reservation reservation){
        return repository.save(reservation);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public String setCode(Long id){
        Reservation res = repository.findById(id).orElseThrow(() -> new RuntimeException("Reservation does not exist"));
        int code = random.nextInt(999999);
        res.setCode(String.format("%06d", code));
        repository.save(res);
        return String.format("%06d", code);
    }

    public Integer getRoomIdByCodeAndName(String code, String name){
        return repository.getRoomIdByCodeAndName(code, name).orElse(-1);
    }

    public Integer getRoomIdByCode(String code){
        return repository.getRoomIdByCode(code).orElse(-1);
    }
}
