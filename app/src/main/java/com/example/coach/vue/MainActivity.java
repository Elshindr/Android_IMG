package com.example.coach.vue;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    /**
     * Creation de l'interface graphique d'accueil
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    /**
     * initialisations
     */
    private void init(){
        Controle.getInstance(this);
        creerMenu();
    }

    /**
     * Methode qui initie les objets graphiques des activictés de calcul et d'historique
     */
    private void creerMenu(){
        ecouteMenu((ImageButton) findViewById(R.id.btnMonIMG), CalculActivity.class);
        ecouteMenu((ImageButton) findViewById(R.id.btnHistorique), HistoActivity.class);
    }


    /**
     * Methode evenementielle pour lancer le retour vers l'activité d'accueil
     * @param btnImg
     * @param classe
     */
    private void ecouteMenu(ImageButton btnImg, Class classe){
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}