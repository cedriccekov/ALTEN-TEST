package com.alten.hotel.repositories;

import com.alten.hotel.entities.Chambre;
import com.alten.hotel.entities.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutRepository extends JpaRepository<Statut,Integer> {
}
