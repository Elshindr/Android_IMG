package com.example.coach.outils;

import android.os.AsyncTask;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Classe AccesWS technique de connexion au web service
 */
public class AccesWS extends AsyncTask<String, Integer, Long> {

    // propriétés
    public AsyncResponse delegate=null; // gestion du retour asynchrone
    private SoapObject request;
    private Object reponse = null;
    private HttpTransportSE androidHttpTransport;
    private String namespace;
    private String url;
    private String methode;

    /**
     * Constructeur de la Classe AccesWS
     */
    public AccesWS(String url, String namespace, String methode){
        this.url = url;
        this.namespace = namespace;
        this.methode = methode;
        request = new SoapObject(namespace, methode);
    }

    /**
     * Méthode d'ajout de paramètres pour la méthode invoquée
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur) {
        request.addProperty(nom, valeur);
    }

    /**
     * Méthode appelée par la méthode execute
     * permet d'envoyer au serveur une liste de paramètres en GET
     * @param urls contient l'adresse du serveur dans la case 0 de urls
     * @return null
     */

    @Override
    protected Long doInBackground(String... urls) {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        androidHttpTransport = new HttpTransportSE(url);
        try
        {
            androidHttpTransport.call(namespace+methode, envelope);
            reponse =  (String)envelope.getResponse();
        }
        catch (Exception aE)
        {
            aE.printStackTrace ();
        }
        return null;
    }

    /**
     * Méthode appellée sur le retour du serveur, envoi l'information retournée à processFinish
     * @param result
     */
    @Override
    protected void onPostExecute(Long result) {
        // resultsRequestSOAP contient l'information récupérée
        if(reponse != null) {
            delegate.processFinish(reponse.toString());
        }
    }

}
