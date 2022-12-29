/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alten.hotel.controllers;

import com.alten.hotel.entities.Reservation;
import com.alten.hotel.service.ChambreService;
import com.alten.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author HP
 */

@RestController
@RequestMapping("/hotel")
public class hotelController {

    @Autowired
    private ChambreService chambreService;


    @Autowired
    private ReservationService reservationService;
    
     @GetMapping("/plage")
    public String plage(){

        return this.chambreService.plage();
    }


    @GetMapping("/check")
    public String checkRoom(){

        return this.chambreService.checkAvaibility();
    }

    @PostMapping("/booking")
    public ResponseEntity<Object> booking(@RequestBody Reservation reservation) throws Exception {

          return this.reservationService.createReservation(reservation);
    }


    @GetMapping("/cancel/{id}")
    public ResponseEntity<Object> cancelBooking(@PathVariable("id") Integer id) throws Exception {

        return this.reservationService.deleteReservation(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateBooking(@RequestBody Reservation reservation,@PathVariable("id") Integer id) throws Exception {

        return this.reservationService.updateReservation(reservation,id);
    }





    
}
