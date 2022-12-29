package com.alten.hotel.serviceImpl;

import com.alten.hotel.entities.Chambre;
import com.alten.hotel.entities.Reservation;
import com.alten.hotel.exception.NombreSejourException;
import com.alten.hotel.exception.ReservationCalendrierException;
import com.alten.hotel.exception.ReservationException;
import com.alten.hotel.repositories.ChambreRepository;
import com.alten.hotel.repositories.ReservationRepository;
import com.alten.hotel.repositories.StatutRepository;
import com.alten.hotel.service.ChambreService;
import com.alten.hotel.service.ReservationService;
import com.alten.hotel.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reserv;

    @Autowired
    private ChambreRepository chbre;


    @Autowired
    private StatutRepository statut;


                            // create reservation
    @Override
    public ResponseEntity<Object>  createReservation(Reservation c) throws Exception {


        /*if(c.getNbre().intValue()>3)
        {
            throw new NombreSejourException();
        }*/
        Chambre ch=chbre.check();
        Reservation reservSuccess=null;
        Date today=new Date();

        boolean checkin=false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date dateEntree= sdf.parse(c.getDate_entree());
             // format today date without time
        String todayStr=sdf.format(today);
        Date dayStr=sdf.parse(todayStr);

        Date dateSortie=Util.calculdateSortie(dateEntree,c.getNbre().intValue());

        int jour= Util.calculjour(today,dateEntree);

        System.out.println("date sortie" + sdf.format(dateSortie));
        System.out.println("jour " + jour);
        System.out.println("jour " + dayStr);



             if(dateEntree.before(dayStr)){
                  throw new ReservationException();

             }  else{


                                     //Cas de chambre disponible
        if (ch.getAvailable()){

            if(jour>30){ throw new ReservationCalendrierException();
            }

            else{
                   if(c.getNbre().intValue()>3)
                   { throw new NombreSejourException();
                   }
                   else{
                       c.setCodeStatut(statut.findById(3).get());
                       c.setCodeChambre(chbre.findById("MAT23").get());
                       c.setDate_sortie(sdf.format(dateSortie));
                       reservSuccess=reserv.save(c);

                       Chambre chambre=chbre.findById("MAT23").get();
                       chambre.setAvailable(Boolean.FALSE);
                       chbre.save(chambre);
                   }

            }

        }// available
                          // Cas de chambre non disponible
        else{
            List<Reservation> bookList =this.reserv.bookingFuture();
              for(Reservation book:bookList){

                  Date bookDateEntree=sdf.parse(book.getDate_entree());
                  Date bookDateSortie=sdf.parse(book.getDate_sortie());

//                  if(dateEntree.equals(bookDateEntree) || dateEntree.equals(bookDateSortie) ){throw new ReservationException();}
//                  else if (dateEntree.before(bookDateEntree) && dateSortie.after(bookDateEntree)){throw new ReservationException();}
//                  else if (dateEntree.after(bookDateEntree) &&
//                          (dateSortie.before(bookDateSortie) ||  (dateSortie.equals(bookDateSortie) || dateSortie.after(bookDateSortie) )  )  ){throw new ReservationException();}
//
                  if(dateEntree.equals(bookDateEntree) || dateEntree.equals(bookDateSortie) ) {checkin=true;}
                  if(dateEntree.equals(today)) {checkin=true;}
                  if (dateEntree.before(bookDateEntree) && (dateSortie.after(bookDateEntree) || dateSortie.equals(bookDateEntree))) {checkin=true;}
                  if (dateEntree.after(bookDateEntree) &&
                          (dateSortie.before(bookDateSortie) ||  (dateSortie.equals(bookDateSortie)  ) ) ) {checkin=true;}



              }  // for
                                if (checkin){
                                        throw new ReservationException();}

                                else{
                                     if(jour>30){
                                          throw new ReservationCalendrierException();}

                                    else{
                                        if(c.getNbre().intValue()>3)
                                        {throw new NombreSejourException();}
                                        else{
                                            c.setCodeStatut(statut.findById(3).get());
                                            c.setCodeChambre(chbre.findById("MAT23").get());
                                            c.setDate_sortie(sdf.format(dateSortie));
                                            reservSuccess=reserv.save(c);

                                            Chambre chambre=chbre.findById("MAT23").get();
                                            chambre.setAvailable(Boolean.FALSE);
                                            chbre.save(chambre);

                                        }
                                    }

                                }




        } // else chambre non disponible

             } // else date entree is before today
        return new ResponseEntity<>("reservation créée - numéro  " +  reservSuccess.getId()  + "  date entree est "+
                reservSuccess.getDate_entree(),
                HttpStatus.CREATED);
    }

    /*@Override
    public ResponseEntity<Object> updateReservation(Reservation c, Integer id) throws Exception {
        Chambre ch=chbre.check();
        Reservation reservSuccess=null;
        Date today=new Date();
        boolean checkin=false;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date dateEntree= sdf.parse(c.getDate_entree());

        Date dateSortie=Util.calculdateSortie(dateEntree,c.getNbre().intValue());

        int jour= Util.calculjour(today,dateEntree);

        System.out.println("date sortie" + sdf.format(dateSortie));
        System.out.println("jour " + jour);

       //delete(id);

       // Reservation re=this.reserv.findById(id).get();





                //if (re!=null){


                    if(dateEntree.before(today)){
                        throw new ReservationException();

                    }  else{


                        //Cas de chambre disponible
                        if (ch.getAvailable()){

                            if(jour>30){ throw new ReservationCalendrierException();
                            }

                            else{
                                if(c.getNbre().intValue()>3)
                                { throw new NombreSejourException();
                                }
                                else{
                                    delete(id);

                                    c.setCodeStatut(statut.findById(3).get());
                                    c.setCodeChambre(chbre.findById("MAT23").get());
                                    c.setDate_sortie(sdf.format(dateSortie));
                                    reservSuccess=reserv.save(c);

                                    Chambre chambre=chbre.findById("MAT23").get();
                                    chambre.setAvailable(Boolean.FALSE);
                                    chbre.save(chambre);

                                }

                            }

                        }// available
                        // Cas de chambre non disponible
                        else{
                            List<Reservation> bookList =this.reserv.bookingFuture();
                            for(Reservation book:bookList){

                                Date bookDateEntree=sdf.parse(book.getDate_entree());
                                Date bookDateSortie=sdf.parse(book.getDate_sortie());


                                if(dateEntree.equals(bookDateEntree) || dateEntree.equals(bookDateSortie) ) {checkin=true;}
                                if (dateEntree.before(bookDateEntree) && dateSortie.after(bookDateEntree)) {checkin=true;}
                                if (dateEntree.after(bookDateEntree) &&
                                        (dateSortie.before(bookDateSortie) ||  (dateSortie.equals(bookDateSortie) || dateSortie.after(bookDateSortie) ) ) ) {checkin=true;}



                            }  // for
                            if (checkin){
                                throw new ReservationException();}

                            else{
                                if(jour>30){
                                    throw new ReservationCalendrierException();}

                                else{
                                    if(c.getNbre().intValue()>3)
                                    {throw new NombreSejourException();}
                                    else{
                                        delete(id);

                                        c.setCodeStatut(statut.findById(3).get());
                                        c.setCodeChambre(chbre.findById("MAT23").get());
                                        c.setDate_sortie(sdf.format(dateSortie));
                                        reservSuccess=reserv.save(c);

                                        Chambre chambre=chbre.findById("MAT23").get();
                                        chambre.setAvailable(Boolean.FALSE);
                                        chbre.save(chambre);

                                    }
                                }

                            }




                        } // else chambre non disponible

                    } // else date entree is before today

//                } else {
//
//                    return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
//                }


        return new ResponseEntity<>("reservation mise à jour  avec numero   " +  reservSuccess.getId()  + "  date entree est "+
                reservSuccess.getDate_entree(),
                HttpStatus.CREATED);

    }*/

                              // delete reservation
    @Override
    public ResponseEntity<Object> deleteReservation(Integer id) throws Exception {
                 Reservation re=this.reserv.findById(id).get();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                   Date today=new Date();
        Date dateEntree= sdf.parse(re.getDate_entree());

                                      if (re!=null){
                                              // if(today.before(dateEntree)){
                                                   if(re.getCodeStatut().getLibelle().equalsIgnoreCase("COMING")){

                                                   this.reserv.deleteById(id);
                                                   List<Reservation> bookingNext=this.reserv.bookingFuture();
                                                   if (bookingNext.isEmpty()){
                                                       Chambre c=this.chbre.findById("MAT23").get();
                                                       c.setAvailable(Boolean.TRUE);
                                                       this.chbre.save(c);
                                                   }


                                               }

                                               else {

                                                   return new ResponseEntity<>("Impossible suppression ,Reservation debutée",HttpStatus.OK);
                                               }


                                      } else {

                                          return new ResponseEntity<>("Reservation not found",HttpStatus.NO_CONTENT);
                                      }
                       return new ResponseEntity<>("Reservation supprimée",HttpStatus.OK);
    }




                                 // update reservation
    public ResponseEntity<Object> updateReservation(Reservation c, Integer id) throws Exception {

        Reservation reservSuccess=null;

        Reservation re=this.reserv.findById(id).get();


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date dateEntreeC= sdf.parse(c.getDate_entree());
        Date dateEntreeRe= sdf.parse(re.getDate_entree());


         if ( (!dateEntreeC.equals(dateEntreeRe)) ||  (!c.getNbre().equals(re.getNbre()))){

             return new ResponseEntity<>("Changement de nombre de sejours ou date entree ,Veuillez annuler la réservation et créer une nouvelle",HttpStatus.OK);
         } else{

               if (re!=null){
                       re.setTel(c.getTel());
                       re.setNom(c.getNom());
                   reservSuccess=reserv.save(re);


               }

         }
        return new ResponseEntity<>("reservation mise à jour  numéro   " +  reservSuccess.getId()  + "  date entree est "+
                reservSuccess.getDate_entree(),
                HttpStatus.CREATED);
    }



}
