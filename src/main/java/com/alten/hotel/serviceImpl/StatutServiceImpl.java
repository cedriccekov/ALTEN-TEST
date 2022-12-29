package com.alten.hotel.serviceImpl;

import com.alten.hotel.entities.Statut;
import com.alten.hotel.repositories.StatutRepository;
import com.alten.hotel.service.ReservationService;
import com.alten.hotel.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatutServiceImpl implements StatutService{

    @Autowired
    private StatutRepository s;


    @Override
    public Statut createStatut(Statut c) {
        Statut st=this.s.save(c);
        return st;
    }
}
