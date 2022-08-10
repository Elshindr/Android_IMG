package com.example.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe Controle du Controleur
 */
public final class Controle {

    private static Controle instance = null ;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    private static AccesDistant accesDistant;
    private static AccesLocal accesLocal;
    private static Context context;
    private ArrayList<Profil> lesProfils;

    /**
     * Constructeur privé de la Classe Controle
     */
    private Controle(){
        super();
    }

    /**
     * Méthode de récupération de l'instance unique de Controle
     * @return instance
     */
    public static final Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            if(context != null) {
                Controle.context = context;
            }

           // accesLocal = new AccesLocal(context);
           // profil = accesLocal.recupDernier();

            accesDistant = new AccesDistant();
            accesDistant.envoi("tous", null);
        }
        return Controle.instance;
    }

    /**
     * Méthode de création d'un profil
     * @param poids int le poids
     * @param taille en cm
     * @param age int age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe){
        Profil unProfil = new Profil(new Date(), poids, taille, age, sexe);
        lesProfils.add(unProfil);
        accesDistant.envoi("enreg", unProfil.convertToJSONObject());
    }

    /**
     * Méthode de modification d'un profil
     * @param poids int le poids
     * @param taille int la taille
     * @param age int age
     * @param sexe int sexe ( 1 Femme / 0 Homme)
     */
    public void modifierProfil(Integer poids, Integer taille, Integer age, Integer sexe){
        Profil updateProfil = new Profil(profil.getDateMesure(), poids, taille, age, sexe);

        lesProfils.remove(profil);
        lesProfils.add(updateProfil);
        accesDistant.envoi("modif", updateProfil.convertToJSONObject());
    }

    /**
     * Méthode de suppression d'un profil
     * @param profil un objet profil
     */
    public void delProfil(Profil profil){
        accesDistant.envoi("suppr", profil.convertToJSONObject());
        lesProfils.remove(profil);
    }

    /**
     * Méthode de getter sur l'img du profil
     * @return img une image
     */
    public float getImg(){
        if(lesProfils.size() == 0){
            return 0;
        }else {
            return lesProfils.get(lesProfils.size() - 1).getImg();
        }
    }

    /**
     * Méthode de getter sur le message du profil
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
     * Méthode de getter sur le poids du profil
     * @return poids
     */
    public Integer getPoids(){
        if(profil != null){
            return profil.getPoids();
        }
        return null;
    }

    /**
     * Méthode de getter sur la taille du profil
     * @return taille en cm
     */
    public Integer getTaille(){
        if(profil != null){
            return profil.getTaille();
        }
        return null;
    }

    /**
     * Méthode de getter sur l'age du profil
     * @return age int
     */
    public Integer getAge(){
        if(profil != null){
            return profil.getAge();
        }
        return null;
    }
    /**
     * Méthode de getter sur le sexe du profil
     * @return sexe int
     */
    public Integer getSexe(){
        if(profil != null){
            return profil.getSexe();
        }
        return null;
    }

    /**
     * Méthode de setter sur le profil
     * @param profil un objet de type profil
     */
    public void setProfil(Profil profil){
        Controle.profil = profil;
    }

    /**
     * Méthode de getter sur lesProfils
     * @return lesProfils Liste de profils
     */
    public ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

    /**
     * Méthode de setter sur lesProfils
     * @param lesProfils
     */
    public void setLesProfils(ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
    }
}