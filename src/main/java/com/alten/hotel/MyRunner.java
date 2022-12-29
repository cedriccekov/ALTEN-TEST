package com.alten.hotel;

import com.alten.hotel.entities.Chambre;
import com.alten.hotel.entities.Reservation;
import com.alten.hotel.entities.Statut;
import com.alten.hotel.repositories.ChambreRepository;
import com.alten.hotel.repositories.ReservationRepository;
import com.alten.hotel.repositories.StatutRepository;
import com.alten.hotel.service.ChambreService;
import com.alten.hotel.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private ChambreRepository c;

    @Autowired
    private StatutRepository s;

    @Autowired
    private ReservationRepository r;


    public MyRunner(ChambreRepository rc,StatutRepository rs,ReservationRepository r) {
        this.c = rc;
        this.s=rs;
        this.r=r;
    }

    @Override
    public void run(String... args) throws Exception {
        s.deleteAll();
        c.deleteAll();
        r.deleteAll();

        s.save(new Statut("CLOSE"));
        s.save(new Statut("IN PROGRESS"));
        s.save(new Statut("COMING"));
        c.save(new Chambre("MAT23","suite"));

        Reservation serv=new Reservation();
        serv.setNbre(2);
        serv.setNom("Louis");
        serv.setTel(91952354);
        serv.setDate_entree("01-01-2023");
        serv.setDate_sortie("03-01-2023");
        serv.setCodeChambre(c.findById("MAT23").get());
        serv.setCodeStatut(s.findById(3).get());
        r.save(serv);
        Chambre ch=c.findById("MAT23").get();
               ch.setAvailable(Boolean.FALSE);
               c.save(ch);



        System.out.println("oufff");






    }
}
