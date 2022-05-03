package com.example.coach.modele;

import junit.framework.TestCase;

import java.util.Date;

/**
 * Classe ProfilTest hérite de TestCase
 */
public class ProfilTest extends TestCase {
    // création d’un profil : femme de 67kg, 1m65, 35 ans
    private Profil profil = new Profil(new Date(),  165,  35,  0, 1);
    // résultat de l’img correspondant
    private float img = (float)1600.0966;
    // message correspondant
    private String message = "trop élevé" ;

    /**
     * Methode de test du getter sur image getImg
     */
    public void testGetImg() {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

    /**
     * Methode de test du getter sur message getMessage
     */
    public void testGetMessage() {
        assertEquals(message, profil.getMessage());
    }
}