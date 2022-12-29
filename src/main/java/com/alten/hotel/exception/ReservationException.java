package com.alten.hotel.exception;

import com.alten.hotel.entities.Reservation;

public class ReservationException extends Exception {

    public ReservationException(){
         super("Impossible de faire une réservation dans cette période ,Veuillez choisir une autre date");
    }
}
