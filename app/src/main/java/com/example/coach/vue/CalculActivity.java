package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.*;
import com.example.coach.controleur.Controle;
import com.example.coach.outils.MesOutils;

public class CalculActivity extends AppCompatActivity {

    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;
    private Controle controle;

    /**
     * Création de l'interfac graphique
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }

    /**
     * Initialisations :
     * récupération des composants graphiques et du contrôleur
     * demande d'écoute du clic sur le bouton calcul
     */
    private void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        controle = Controle.getInstance(this);
        ecouteCalcul();
        ecouteRetourMenu();
        recupProfil();
    }

    /**
     * méthode événementielle sur le clic de l'image du retour au menu
     */
    private void ecouteRetourMenu(){
        ImageButton btnRetourMenu = (ImageButton)findViewById(R.id.btnRetourCalcul);
        btnRetourMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    /**
     * Méthode événementielle sur le clic du bouton calculer
     * récupère les informations saisies et demande l'affichage du résultat
     */
    private void ecouteCalcul(){
        btnCalc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Integer poids = 0, taille = 0, age = 0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){}
                Integer sexe = 0;
                if (rdHomme.isChecked()){
                    sexe = 1;
                }
                if (poids==0 || taille==0 || age==0){
                    Toast.makeText(CalculActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                }else{
                    afficheResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * calcule et affiche le l'img (le smiley et le message correspondant)
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age, Integer sexe){
        controle.creerProfil(poids, taille, age, sexe);
        String message = controle.getMessage();
        float img = controle.getImg();
        if (message.equals("Normal")){
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setTextColor(Color.GREEN);
        }else if(message.equals("Trop faible")){
            imgSmiley.setImageResource(R.drawable.maigre);
            lblIMG.setTextColor(Color.RED);
        }else if(message.equals("Trop élevé")){
            imgSmiley.setImageResource(R.drawable.graisse);
            lblIMG.setTextColor(Color.RED);
        }
        lblIMG.setText(MesOutils.format2Decimal(img)+" : IMG "+message);
    }

    /**
     * récupère les informations du profil (si pas nul) et les affiche
     */
    public void recupProfil(){
        if(controle.getTaille() != null){
            txtTaille.setText(controle.getTaille().toString());
            txtPoids.setText(controle.getPoids().toString());
            txtAge.setText(controle.getAge().toString());
            if(controle.getSexe() == 1){
                rdHomme.setChecked(true);
            }else{
                rdFemme.setChecked(true);
            }
        }
        controle.setProfil(null);
    }

}