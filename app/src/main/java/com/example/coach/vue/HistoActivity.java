package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe HistoActivity
 * Affichage de l'historique des calculs de profil
 */
public class HistoActivity extends AppCompatActivity {

    private Controle controle;

    /**
     * Méthode appellée à la création
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        init();
    }

    /**
     * Méthode d'initialisation des composants
     */
    private void init(){
        controle = Controle.getInstance(this);
        ecouteRetourMenu();
        creerListe();
    }

    /**
     * Méthode de création de la liste adapter
     */
    private void creerListe(){
        ArrayList<Profil> liste;
        liste = controle.getLesProfils();
        if(liste != null){
            Collections.sort(liste, Collections.<Profil>reverseOrder());
            ListView listView = (ListView)findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(HistoActivity.this, liste);
            listView.setAdapter(adapter);
        }
    }

    /**
     * M2thode de demande d'affichage du profil dans CalculActivity
     * @param profil
     */
    public void afficheProfil(Profil profil){
        controle.setProfil(profil);
        Intent intent = new Intent(HistoActivity.this, CalculActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * Evénement sur le clic de l'image du retour au menu
     */
    private void ecouteRetourMenu(){
        ImageButton btnRetourMenu = (ImageButton)findViewById(R.id.btnRetourHisto);
        btnRetourMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

}