package com.example.coach.modele;

public class Profil {

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
    public Profil(int poids, int taille, int age, int sexe) {
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
}
