package com.example.coach.modele;

import com.example.coach.outils.MesOutils;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profil implements Serializable {

    // constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    private int poids;
    private int taille;
    private int age;
    private int sexe;
    private float img;
    private String message;
    private Date dateMesure;


    /**
     * Constructeur de la classe Profil
     * @param dateMesure
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public Profil(Date dateMesure, int poids, int taille, int age, int sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateMesure() {
        return dateMesure;
    }
    /**
     * Methode qui calcul l'IMG puis valorise la propriété img
     */
    private void calculIMG(){
        float tailleCM = ((float) taille) / 100;
        img = (float)((1.2 * poids / (Math.pow(tailleCM, 2))) + (0.23 * age) - (10.83 * sexe) - 5.4);
    }

    /**
     * Methode qui valorise la prorpriété message selon l'IMG calculé
     */
    private void resultIMG(){
        message = "Normal";
        Integer min = minFemme, max = maxFemme;
        if(sexe == 1){
            min = minHomme;
            max = maxHomme;
        }
        if(img<min){
            message = "Trop faible";
        }else{
            if(img>max){
                message = "Trop élevé";
            }
        }
    }

    /**
     * convertit les informations du profil au format JSON
     * @return un JSONArray contenant les informations du profil
     */
    public JSONArray convertToJSONArray(){
        List uneliste = new ArrayList();
        uneliste.add(MesOutils.convertDateToString(dateMesure));
        uneliste.add(poids);
        uneliste.add(taille);
        uneliste.add(age);
        uneliste.add(sexe);

        return new JSONArray(uneliste);
    }
}
