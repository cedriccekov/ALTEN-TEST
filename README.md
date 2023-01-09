# ALTEN-TEST

# ALTEN-TEST

![Fancy logo](./light.png#gh-light-mode-only)

Java 8 -
Spring Boot 2.7.7 - 
Memory Database H2

 Database H2 URL : http://localhost:6060/h2-console

     - Vérifier la disponibilité de la chambre
     
                    GET : http://localhost:6060/hotel/check 
 
 
 - Consulter la plage des dates déjà réservees
                GET : http://localhost:6060/hotel/plage 
 
 
 
 -  Faire une reservation
  
              POST : http://localhost:6060/hotel/booking 
                Exemple:  
 {
"nom":"elom",
"tel":91452174,
"nbre":3,
"date_entree":"28-12-2022"

}



    - Update une reservation
                  PUT : http://localhost:6060/hotel/update/1
           Exemple:  
 {
"nom":"elom",
"tel":91452174,
"nbre":3,
"date_entree":"28-12-2022"

}

 - Annuler une reservation

     GET : http://localhost:6060/hotel/cancel/2


 - Actuator links (Suivi de maintenance de API par IT departement)
  
      Basic Auth to access  (
             Username: admin
             Password: admin90
             )
  
         GET: http://localhost:6060/actuator/

        Exemple : http://localhost:6060/actuator/health, http://localhost:6060/actuator/metrics;...
