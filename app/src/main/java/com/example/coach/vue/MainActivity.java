package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.*;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {
    private TextView txtPoids;
    private TextView txtTaille;
    private TextView txtAge;
    private RadioButton rdFemme;
    private RadioButton rdHomme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Methode de récupération des composants graphiques et d'initialisation des propriétés et appelle l'unique instance du controleur
     */
    private void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        btnCalc = (Button) findViewById(R.id.btnCalc);

        controle = Controle.getInstance(this);

        ecouteCalcul();
        //recupProfil();
    }

    /**
     * Methode evenementielle qui ecoute le click sur le bouton calculer
     */
    private void ecouteCalcul(){
        btnCalc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                Integer sexe = 0;

                if(!rdFemme.isChecked()){
                    sexe = 1;
                }

                try{
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){

                }

                if(poids.equals(0) || taille.equals(0) || age.equals(0)){
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                }
                else{

                    afficheResult(poids, taille,age, sexe);
                }
            }
        });

    }

    /**
     * Methode qui affiche les resultats du calcul de l'img
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(int poids, int taille, int age, int sexe){
        String message;
        float img;
        controle.creerProfil(poids, taille, age, sexe, this);
        img = controle.getImg();
        message = controle.getMessage();


        if(message.equals("Normal")){
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setTextColor(Color.GREEN);
        }
        else if(message.equals("Trop élevé")){
            imgSmiley.setImageResource(R.drawable.graisse);
            lblIMG.setTextColor(Color.RED);
        }
        else{
            imgSmiley.setImageResource(R.drawable.maigre);
            lblIMG.setTextColor(Color.RED);
        }
        lblIMG.setText(String.format("%.01f", img)+ message);
    }

    /**
     * Methode qui récupére les informations de la classe Profil contenu dans la serialisation
     */
    private void recupProfil(){
        if(controle.getTaille() != null && controle.getAge() != null && controle.getPoids() != null && controle.getSexe() != null){

            Integer.parseInt(txtPoids.getText().toString());
            txtTaille.setText(Integer.parseInt(controle.getTaille().toString()));
            txtAge.setText(Integer.parseInt(controle.getAge().toString()));
            txtPoids.setText((Integer.parseInt(controle.getPoids().toString())));
            if(controle.getSexe() == 0){

                rdFemme.setChecked(true);
            }
            else if(controle.getSexe() == 1){
                rdHomme.setChecked(true);
            }
            btnCalc.performClick();
        }
    }
}