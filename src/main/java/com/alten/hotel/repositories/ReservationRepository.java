package com.alten.hotel.repositories;

import com.alten.hotel.entities.Chambre;
import com.alten.hotel.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    @Query("SELECT t FROM  Reservation t WHERE t.codeStatut.libelle='COMING'")
    List<Reservation> bookingFuture();

}
