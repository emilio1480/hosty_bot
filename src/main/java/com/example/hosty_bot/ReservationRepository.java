package com.example.hosty_bot;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Long> {
    @Query("Select r.roomId from Reservation r where r.code = ?1 and r.name = ?2")
    Optional<Integer> getRoomIdByCodeAndName(String code, String name);

    @Query("Select r.roomId from Reservation r where r.code = ?1")
    Optional<Integer> getRoomIdByCode(String code);
}
