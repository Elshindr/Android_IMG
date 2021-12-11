package com.example.coach.modele;

import junit.framework.TestCase;

public class ProfilTest extends TestCase {
    // création d’un profil : femme de 67kg, 1m65, 35 ans
    private Profil profil = new Profil(67, 165, 35, 0);
    // résultat de l’img correspondant
    private float img = (float)32.2 ;
    // message correspondant
    private String message = "Trop élevé" ;

    public void testGetImg() {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

    public void testGetMessage() {
        assertEquals(message, profil.getMessage());
    }
}