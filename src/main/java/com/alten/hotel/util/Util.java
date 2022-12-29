package com.alten.hotel.util;

import java.util.Date;

public class Util {

    public static int calculjour(Date date1, Date date2)
    {
        long difference=(date2.getTime() - date1.getTime());
        return (int)(difference / (1000*60*60*24));
    }
    public static Date calculdateSortie(Date date1,int nbJour)
    {
        long somme=(date1.getTime() +(1000*60*60*24)* nbJour);
        return new Date(somme);
    }
}
