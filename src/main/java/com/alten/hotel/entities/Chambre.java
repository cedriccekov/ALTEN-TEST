package com.alten.hotel.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter

@ToString
@Table(name = "CHAMBRE")
public class Chambre {

    @Id
    private String matricule;

    @Column(name = "desc")
    private String desc;



    @Column(name = "available",nullable = false)
    private Boolean available=true;

    @OneToMany(mappedBy = "codeChambre")
    private List<Reservation> reservationList;


    public Chambre(){

    }

    public Chambre(String matricule,String desc){
        this.matricule=matricule;
        this.desc=desc;
    }




}
