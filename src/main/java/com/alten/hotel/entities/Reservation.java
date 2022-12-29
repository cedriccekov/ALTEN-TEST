package com.alten.hotel.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter

@ToString
@NoArgsConstructor
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nbre_jours")
    private Integer nbre;

    @Column(name = "date_entree")
    private String date_entree;

    @Column(name = "date_sortie")
    private String date_sortie;


    @Column(name = "nom")
    private String nom;


    @Column(name = "tel")
    private Integer tel;

    @JoinColumn(name = "statut_id", referencedColumnName = "idStatut")
    /*     */   @ManyToOne(optional = false)
    /*     */  private Statut codeStatut;

    @JoinColumn(name = "matricule", referencedColumnName = "matricule")
    /*     */   @ManyToOne(optional = false)
    /*     */  private Chambre codeChambre;




}
