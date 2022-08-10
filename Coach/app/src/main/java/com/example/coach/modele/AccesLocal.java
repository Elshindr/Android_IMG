package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coach.outils.AccesREST;
import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import org.json.JSONObject;

import java.util.Date;

/**
 * Classe AccesLocal
 * Permet de récupérer les informations dans la base local Sqlite
 */
public class AccesLocal {
    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * Constructeur : valorise l'accès à la BDD
     * @param activity
     */
    public AccesLocal(Context activity){
       accesBD = new MySQLiteOpenHelper(activity, nomBase, versionBase);
    }

    /**
     * Méthode d'ajout d'un profil dans la BDD
     * @param profil
     */
    public void ajout(Profil profil){
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("dateMesure", profil.getDateMesure().toString());
        values.put("poids", profil.getPoids());
        values.put("taille", profil.getTaille());
        values.put("age", profil.getAge());
        values.put("sexe", profil.getSexe());
        bd.insert("profil", null, values);
        bd.close();
    }

    /**
     * Méthode qui retourne le dernier profil enregistré dans la BDD
     * @return dernier profil
     */
    public Profil recupDernier(){
        Profil profil = null;
        bd = accesBD.getReadableDatabase();
        String req = "select * from profil";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date dateMesure = MesOutils.convertStringToDate(curseur.getString(0));
            Log.d("date", "***************** dateMesure = " + dateMesure);
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(dateMesure, poids, taille, age, sexe);
        }
        curseur.close();
        return profil;
    }

}