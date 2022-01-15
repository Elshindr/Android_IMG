package com.example.coach.modele;

import com.example.coach.outils.MesOutils;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;

public class Profil implements Serializable, Comparable {

    // constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 26; // gros si au dessus

    private Date dateMesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;

    /**
     * constructeur : valorise poids, taille, age, sexe
     * et appelle les méthodes pour valoriser img et message
     * @param dateMesure
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public Profil(Date dateMesure, Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }

    /**
     * getter sur dateMesure
     * @return dateMesure
     */
    public Date getDateMesure() {
        return dateMesure;
    }

    /**
     * getter sur poids
     * @return poids
     */
    public Integer getPoids() {
        return poids;
    }

    /**
     * getter sur taille
     * @return taille en cm
     */
    public Integer getTaille() {
        return taille;
    }

    /**
     * getter sur age
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * getter sur sexe
     * @return 1 pour homme, 0 pour femme
     */
    public Integer getSexe() {
        return sexe;
    }

    /**
     * getter sur img
     * @return img
     */
    public float getImg() {
        return img;
    }

    /**
     * getter sur message
     * @return "normal", "trop faible", "trop élevé"
     */
    public String getMessage() {
        return message;
    }

    /**
     * calcul de l'img
     */
    private void calculIMG(){
        float taillecm = ((float)taille)/100;
        this.img = (float)((1.2 * poids/(taillecm*taillecm)) + (0.23 * age) - (10.83 * sexe) - 5.4);
    }

    /**
     * création du message suivant la valeur de l'img et des constantes
     */
    private void resultIMG(){
        message = "normal";
        Integer min = minFemme, max = maxFemme;
        if(sexe == 1){
            min = minHomme;
            max = maxHomme;
        }
        if(img<min){
            message = "trop faible";
        }else{
            if(img>max){
                message = "trop élevé";
            }
        }
    }

    /**
     * convertit les informations du profil au format JSON
     * @return un JSONObject contenant les informations du profil
     */
    public JSONObject convertToJSONObject(){
        // Hashtable<String, Object> liste = new Hashtable<String, Object>();
        Hashtable<String, Object> liste = new Hashtable<String, Object>();
        liste.put("datemesure", MesOutils.convertDateToString(dateMesure));
        liste.put("poids", poids);
        liste.put("taille", taille);
        liste.put("age", age);
        liste.put("sexe", sexe);
        return new JSONObject(liste);
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
