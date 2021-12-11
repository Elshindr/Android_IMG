package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil;

    /**
     * constructeur privé
     */
    private Controle() {
        super();
    }

    /**
     * Classe singleton qui récupére l'instance unique de la classe Controle ou la créée
     * @return instance
     */
    public static final Controle getInstance(){
        if(instance == null) {
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     * Methode qui créé une nouvelle instance de la classe Profil selon les valeurs de poids, de taille, d'age et de sexe fournies
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 0 femme, 1 homme
     */
    public void creerProfil(int poids, int taille, int age, int sexe){
        profil = new Profil(poids, taille, age, sexe);
    }

    /**
     * Methode qui appelle la methode getImg de la classe Profil et récuppére la valeur de img
     * @return img
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     * Methode qui appelle la methode getMessage de la classe Profil et récuppére la valeur de message
     * @return message
     */
    public String getMessage(){
        return profil.getMessage();
    }
}
