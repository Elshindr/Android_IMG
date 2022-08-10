package com.example.coach.modele;

import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.AccesREST;
import com.example.coach.outils.AsyncResponse;
import com.example.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe AccessDistant implements la Classe AsyncResponse
 */
public class AccesDistant implements AsyncResponse {
    //private static final String SERVERADDR = "http://localhost/coach/api/";
    private static final String SERVERADDR = "https://androidcoach.herokuapp.com/";

    private Controle controle;

    /**
     * Constructeur de la Classe AccesDistant
     */
    public AccesDistant(){
        controle = Controle.getInstance(null);
    }

    /**
     * Methode autogénérée
     * Affiche le retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "************" + output);

        try {

            JSONObject retour = new JSONObject(output);
            String message = retour.getString("message");

            if (!message.equals("OK")) {
                Log.d("serveur", "problème retour api rest :" + message);
            } else {
                Log.d("serveur","reponse serveur is " + message);

                JSONArray infos = new JSONArray();
                if(!retour.getString("result").equals("")){
                  infos = retour.getJSONArray("result");

                    // Create array with datas
                    ArrayList<Profil> lesProfils = new ArrayList<>();
                    for (int k = 0; k < infos.length(); k++) {
                        JSONObject info = new JSONObject(infos.get(k).toString());

                        Date dateMesure = MesOutils.convertStringToDate(info.getString("datemesure"), "yyyy-MM-dd hh:mm:ss");
                        Integer poids = info.getInt("poids");
                        Integer taille = info.getInt("taille");
                        Integer age = info.getInt("age");
                        Integer sexe = info.getInt("sexe");
                        Profil profil = new Profil(dateMesure, poids, taille, age, sexe);

                        lesProfils.add(profil);
                    }
                    controle.setLesProfils(lesProfils);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode d'envoi de données vers le serveur distant
     * @param operation
     * @param lesDonneesJSON
     */
    public void envoi(String operation, JSONObject lesDonneesJSON){
        AccesREST accesDonnees = new AccesREST();
        accesDonnees.delegate = this;
        String requesMethod = null;
        switch (operation){
            case "tous"  : requesMethod="GET"; break;
            case "enreg" : requesMethod="POST"; break;
            case "modif" : requesMethod="PUT"; break;
            case "suppr" : requesMethod="DELETE"; break;
            default: break;

        }
        Log.d("envoi"," ::::::::"+requesMethod);

        if (requesMethod != null) {
            accesDonnees.setRequestMethod(requesMethod);
            accesDonnees.addParam("profil");
            if (lesDonneesJSON != null) {
                try {
                    Log.d("envoi", lesDonneesJSON.getString("datemesure"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                accesDonnees.addParam(lesDonneesJSON.toString());
            }
            accesDonnees.execute(SERVERADDR);
        }
    }
}