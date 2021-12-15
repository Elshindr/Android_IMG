package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

import java.util.Date;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveProfil";
    private static AccesLocal accesLocal;

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
    public static final Controle getInstance(Context activity){
        if(Controle.instance == null) {
            Controle.instance = new Controle();
            // Appel pour serialisation
            // recupSerialize(activity);
            accesLocal= new AccesLocal(activity);
            profil = accesLocal.recupDernier();
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
    public void creerProfil(int poids, int taille, int age, int sexe, Context activity){
        profil  = new Profil(new Date(), poids, taille, age, sexe);
        // Appel pour serialisation
        // Serializer.serialize(nomFic, profil, activity);

        accesLocal.ajout(profil);
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

    /**
     * Methode qui verifie que l'objet Profil n'est pas null et renvoi la valeur de taille
     * @return Integer taille
     */
    public Integer getTaille(){
        if(!(profil == null)){
            return profil.getTaille();
        }
        return null;
    }

    /**
     * Methode qui verifie que l'objet Profil n'est pas null et renvoi la valeur de poids
     * @return Integer poids
     */
    public Integer getPoids(){
        if(!(profil == null)){
            return profil.getPoids();
        }
        return null;
    }

    /**
     * Methode qui verifie que l'objet Profil n'est pas null et renvoi la valeur de age
     * @return Integer age
     */
    public Integer getAge(){
        if(!(profil == null)){
            return profil.getAge();
        }
        return null;
    }

    /**
     * Methode qui verifie que l'objet Profil n'est pas null et renvoi la valeur de sexe
     * @return Integer sexe
     */
    public Integer getSexe(){
        if(!(profil == null)){
            return profil.getSexe();
        }
        return null;
    }

    /**
     * Methode qui valorise l'objet profil avec le contenu récupéré de la sérialisation
     * @param activity
     */
    private static void recupSerialize(Context activity){
        profil =  (Profil) Serializer.deSerialize(nomFic, activity);
    }
}
