package com.example.coach.outils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	
	// propriété de création d'une table dans la base de données
	private String creation="create table profil ("
			+ "datemesure TEXT PRIMARY KEY,"
			+ "poids INTEGER NOT NULL,"
			+ "taille INTEGER NOT NULL,"
			+ "age INTEGER NOT NULL,"
			+ "sexe INTEGER NOT NULL);";

    /**
     * Construction de l'accès à une base de données locale
     * @param context
     * @param name
     * @param version
     */
	public MySQLiteOpenHelper(Context context, String name, int version) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

    /**
     * méthode redéfinie appelée automatiquement par le constructeur
     * uniquement si celui-ci repère que la base n'existe pas encore
     * @param db
     */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(creation);
	}

    /**
     * méthode redéfinie appelée automatiquement s'il y a changement de version de la base
     * @param db
     * @param oldVersion
     * @param newVersion
     */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
