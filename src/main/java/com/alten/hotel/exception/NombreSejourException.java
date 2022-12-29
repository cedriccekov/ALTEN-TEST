package com.alten.hotel.exception;

public class NombreSejourException extends Exception {

    public NombreSejourException(){
        super("Nombre de sejour ne doit pas etre supérieur  à 3 jours");
    }
}
