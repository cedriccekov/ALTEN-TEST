package com.alten.hotel;


import com.alten.hotel.entities.Chambre;
import com.alten.hotel.entities.Reservation;
import com.alten.hotel.entities.Statut;
import com.alten.hotel.exception.NombreSejourException;

import com.alten.hotel.repositories.ChambreRepository;
import com.alten.hotel.repositories.ReservationRepository;
import com.alten.hotel.repositories.StatutRepository;
import com.alten.hotel.service.ReservationService;
import com.alten.hotel.serviceImpl.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReservationServiceTests {

    private ReservationService res=new ReservationServiceImpl();



    Reservation serv=null;

    @BeforeEach
    void init(){

        Chambre ch=new Chambre();
        ch.setMatricule("541Z");
        ch.setAvailable(true);
        ch.setDesc("suite");


        Statut st=new Statut();
        st.setIdStatut(2);
        st.setLibelle("COME");


        serv=new Reservation();
        serv.setId(5);
        serv.setNbre(4);
        serv.setNom("joe");
        serv.setTel(91952354);
        serv.setDate_entree("06-01-2023");

      serv.setCodeChambre(ch);
       serv.setCodeStatut(st);

    }

    @Test
    void nombreSejourException(){
        System.out.println(res);
        Throwable exception=assertThrows(NombreSejourException.class, () -> res.createReservation(serv),"message erroné");
//       assertEquals("Nombre de sejour ne doit pas etre supérieur  à 3 jours", exception.getMessage());

    }




}
