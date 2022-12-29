package com.alten.hotel.repositories;

import com.alten.hotel.entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChambreRepository extends JpaRepository<Chambre,String> {

    @Query("SELECT t FROM  Chambre t WHERE t.matricule='MAT23'")
    Chambre check();
}
