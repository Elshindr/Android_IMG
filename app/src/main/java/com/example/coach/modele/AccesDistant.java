package com.example.coach.modele;

import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AccesWS;
import com.example.coach.outils.AsyncResponse;
import com.example.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class AccesDistant implements AsyncResponse {

    private static final String NAMESPACE = "http://192.168.1.30/coach/";
    private static final String URL = NAMESPACE + "server.php";
    private Controle controle;

    /**
     * constructeur
     */
    public AccesDistant(){
        controle = Controle.getInstance(null);
    }


    @Override
    public void processFinish(String output) {
        Log.d("serveur", "************" + output);
        String[] message = output.split("%");
        if(message.length > 1){
            if (message[0].equals("enreg")){
                Log.d("enreg", "*********** "+message[1]);
            }else if (message[0].equals(("tous"))){
                try {
                    JSONArray infos = new JSONArray(message[1]);
                    ArrayList<Profil> lesProfils = new ArrayList<Profil>();
                    for(int k=0;k<infos.length();k++) {
                        JSONObject info = new JSONObject(infos.get(k).toString());
                        Date dateMesure = MesOutils.convertStringToDate(info.getString("datemesure"),
                                "yyyy-MM-dd hh:mm:ss");
                        Integer poids = info.getInt("poids");
                        Integer taille = info.getInt("taille");
                        Integer age = info.getInt("age");
                        Integer sexe = info.getInt("sexe");
                        Profil profil = new Profil(dateMesure, poids, taille, age, sexe);
                        lesProfils.add(profil);
                    }
                    controle.setLesProfils(lesProfils);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else if (message[0].equals("Erreur !")){
                Log.d("erreur", "*********** "+message[1]);
            }
        }
    }

    /**
     * envoi de données vers le serveur distant
     * @param methode
     * @param lesDonneesJSON
     */
    public void envoi(String methode, JSONArray lesDonneesJSON){
        AccesWS accesDonnees = new AccesWS(URL, NAMESPACE, methode);
        accesDonnees.delegate = this;
        if(lesDonneesJSON != null) {
            accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        }
        accesDonnees.execute();
    }

}
