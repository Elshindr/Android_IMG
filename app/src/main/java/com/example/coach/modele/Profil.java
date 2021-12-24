package com.example.coach.modele;

import com.example.coach.outils.MesOutils;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profil implements Serializable, Comparable {

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

    /**
     * Getter sur poids
     * @return poids
     */
    public int getPoids() {
        return poids;
    }

    /**
     * Getter sur taille
     * @return taille
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Getter sur age
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * getter sur sexe
     * @return sexe
     */
    public int getSexe() {
        return sexe;
    }

    /**
     * Getter sur IMG
     * @return img
     */
    public float getImg() {
        return img;
    }

    /**
     * Getter sur message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Getter sur datemesure
     * @return date mesure
     */
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

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        return dateMesure.compareTo(((Profil)o).getDateMesure());
    }
}
