package com.alten.hotel.serviceImpl;

import com.alten.hotel.entities.Chambre;
import com.alten.hotel.entities.Reservation;
import com.alten.hotel.repositories.ChambreRepository;
import com.alten.hotel.repositories.ReservationRepository;
import com.alten.hotel.service.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChambreServiceImpl  implements ChambreService {

    @Autowired
    private ChambreRepository c;

    @Autowired
    private ReservationRepository r;

                              // Verifier disponibilité de la chambre
    @Override
    public String checkAvaibility() {

        List<String>filtreList=new ArrayList<>();
        String listString = "";

        Chambre b=this.c.check();
        if (b.getAvailable()){

            return "Chambre  disponible ";
        }
        else{


            return  "Chambre non disponible périodiquement . Veuillez consulter les plages réservées à venir";
        }

    }
                                   // consulter les  reservations avec statut IN COMING
    @Override
    public String plage() {

        List<String>filtreList=new ArrayList<>();
        String listString = "";

            List<Reservation> reservqtionList =this.r.bookingFuture();
            for (Reservation res:reservqtionList){
                filtreList.add("entree  "+ res.getDate_entree()+" - " + " sortie  " + res.getDate_sortie()  );

            }


            for (String s : filtreList)
            {
                listString += s + "\t";
            }

            return  listString;
        }

    }








