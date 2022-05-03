package com.example.coach.outils;

import com.example.coach.modele.Profil;

import junit.framework.TestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe MesOutilsTest hérite de TestCase
 */
public class MesOutilsTest extends TestCase {
    SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss 'GMT+00:00' yyyy");
    Date date;
    {
        try {
            date = formatter.parse("2020-08-07 05:59:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    // création d’un profil : femme de 67kg, 1m65, 35 ans
    private Profil profil = new Profil(new Date(),  165,  35,  0, 1);

    /**
     * Methode de test sur la conversion d'une chaine en Date
     */
    public void testConvertStringToDate() {
        //assertEquals();
    }

    /**
     * Methode de test sur la conversion d'une chaine en Date
     */
    public void testTestConvertStringToDate() {
    }
    /**
     * Methode de test sur la conversion d'une date en chaine
     */
    public void testConvertDateToString() {
    }
}