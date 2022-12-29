package com.alten.hotel.entities;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter

@ToString
@Table(name = "STATUT")
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatut;

    @Column(name = "libelle")
    private String libelle;

    @OneToMany(mappedBy = "codeStatut")
    private List <Reservation> reservationList;


    public Statut() {

    }

    public Statut(String libelle) {
        this.libelle = libelle;
    }



}
