package com.alten.hotel.exception;

public class ReservationCalendrierException extends Exception {

    public ReservationCalendrierException(){

        super("La reservation ne peut se faire au délà de 30 jours");

    }
}
