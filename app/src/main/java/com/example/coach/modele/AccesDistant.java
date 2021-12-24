package com.example.coach.modele;

import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;
import com.example.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class AccesDistant implements AsyncResponse {
    private static final String SERVERADDR = "http://192.168.1.54/coach/serveurcoach.php";
    private Controle controle;


    /**
     * Constructeur de la classe AccesDistant
     */
    public AccesDistant(){
        controle = controle.getInstance(null);
    }


    /**
     * gérer le retour asynchrone du serveur.
     * @param output texte retourne par le serveur
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "************" + output.length());

        String[] message = output.split("%");
        Log.d("mMEEEEEEEEErde",""+ message.length);
        Log.d("mMEEEEEEEEErde",""+ message[0]);
        //Log.d("mMEEEEEEEEErde",""+ message[1]);
        if(message.length > 1){

            if(message[0].equals("enreg")){
                Log.d("enreg", message[1]);
            }
            else if(message[0].equals("tous")){
                Log.d("tous", message[1]);
                try {
                    JSONArray infos = new JSONArray(message[1]);
                    ArrayList<Profil> lesProfils = new ArrayList<Profil>();
                    for(int k = 0; k < infos.length(); k++){
                        JSONObject info = new JSONObject(infos.get(k).toString());
                        Date dateMesure = MesOutils.convertStringToDate(info.getString("datemesure"), "yyyy-MM-dd hh:mm:ss");
                        Integer poids = info.getInt("poids");
                        Integer taille = info.getInt("taille");
                        Integer age = info.getInt("age");
                        Integer sexe = info.getInt("sexe");

                        Profil profil = new Profil(dateMesure, poids, taille, age, sexe);
                        lesProfils.add(profil);
                    }
                    Log.d("AcessDistant", "================"+lesProfils.size());
                    controle.setLesProfils(lesProfils);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            else if(message[0].equals("Erreur !")){
                Log.d("Erreur !", message[1]);
            }
       }
    }

    /**
     * Envoi de données vers le serveur distant
     * @param operation
     * @param lesDonneesJson
     */
    public void envoi(String operation, JSONArray lesDonneesJson){
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;

        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJson.toString());

        accesDonnees.execute(SERVERADDR);
    }
}
