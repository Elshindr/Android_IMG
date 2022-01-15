package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.Profil;

import java.util.ArrayList;
import java.util.Date;

/**
 * Controleur
 */
public final class Controle {

    private static Controle instance = null ;
    private static Profil profil;
    //private static String nomFic = "saveprofil";
    private static AccesDistant accesDistant;
    private static Context context;
    private ArrayList<Profil> lesProfils;

    /**
     * constructeur privé
     */
    private Controle(){
        super();
    }

    /**
     * récupération de l'instance unique de Controle
     * @return instance
     */
    public static final Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            if(context != null) {
                Controle.context = context;
            }
            accesDistant = new AccesDistant();
            accesDistant.envoi("tous", null);
        }
        return Controle.instance;
    }

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe){
        Profil unProfil = new Profil(new Date(), poids, taille, age, sexe);
        lesProfils.add(unProfil);
        accesDistant.envoi("enreg", unProfil.convertToJSONObject());
    }

    /**
     * suppression d'un profil
     * @param profil
     */
    public void delProfil(Profil profil){
        accesDistant.envoi("suppr", profil.convertToJSONObject());
        lesProfils.remove(profil);
    }

    /**
     * getter sur l'img du profil
     * @return img
     */
    public float getImg(){
        if(lesProfils.size() == 0){
            return 0;
        }else {
            return lesProfils.get(lesProfils.size() - 1).getImg();
        }
    }

    /**
     * getter sur le message du profil
     * @return message
     */
    public String getMessage(){
        if(lesProfils.size() == 0){
            return "";
        }else {
            return lesProfils.get(lesProfils.size() - 1).getMessage();
        }
    }

    /**
     * getter sur le poids du profil
     * @return poids
     */
    public Integer getPoids(){
        if(profil != null){
            return profil.getPoids();
        }
        return null;
    }

    /**
     * getter sur la taille du profil
     * @return taille
     */
    public Integer getTaille(){
        if(profil != null){
            return profil.getTaille();
        }
        return null;
    }

    /**
     * getter sur l'age du profil
     * @return age
     */
    public Integer getAge(){
        if(profil != null){
            return profil.getAge();
        }
        return null;
    }
    /**
     * getter sur le sexe du profil
     * @return sexe
     */
    public Integer getSexe(){
        if(profil != null){
            return profil.getSexe();
        }
        return null;
    }

    /**
     * setter sur le profil
     * @param profil
     */
    public void setProfil(Profil profil){
        Controle.profil = profil;
    }

    /**
     * getter sur lesProfils
     * @return lesProfils
     */
    public ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

    /**
     * setter sur lesProfils
     * @param lesProfils
     */
    public void setLesProfils(ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
    }
}
