package com.alten.hotel.service;

import com.alten.hotel.entities.Chambre;
import com.alten.hotel.entities.Reservation;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface ReservationService {

    ResponseEntity<Object> createReservation(Reservation c) throws Exception;
    ResponseEntity<Object> updateReservation(Reservation c,Integer id) throws Exception;
    ResponseEntity<Object> deleteReservation(Integer id) throws Exception;



}
