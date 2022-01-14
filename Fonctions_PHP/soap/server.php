<?php

// première étape : désactiver le cache lors de la phase de test
ini_set("soap.wsdl_cache_enabled", "0");

// on indique au serveur à quel fichier de description il est lié
$serveurSOAP = new SoapServer('coach.wsdl');

// ajouter les fonctions au serveur
$serveurSOAP->addFunction('tous');
$serveurSOAP->addFunction('enreg');
$serveurSOAP->addFunction('suppr');

// lancer le serveur
if ($_SERVER['REQUEST_METHOD'] == 'POST')
{
	$serveurSOAP->handle();
}
else
{
	echo 'désolé, je ne comprends pas les requêtes GET, veuillez seulement utiliser POST';
}


/**
 * connexion à la BDD
 */
function connexionPDO(){
	$login="root";
	$mdp="";
	$bd="coach";
	$serveur="localhost";
	$port="3306";
	try {
		$conn = new PDO("mysql:host=$serveur;dbname=$bd;port=$port", $login, $mdp);
		return $conn;
	} catch (PDOException $e) {
		return "***********************************Erreur de connexion PDO :".$e;
	}
}

/**
 * récupération de tous les profils 
 */
function tous(){
	try{
		$cnx = connexionPDO();
		$req = $cnx->prepare("select * from profil order by datemesure desc");
		$req->execute();
		while($ligne = $req->fetch(PDO::FETCH_ASSOC)){
			$resultat[]= $ligne;
		}
		return("tous%".json_encode($resultat));
	}catch(PDOException $e){
		return "*********************************************Erreur !%".$e->getMessage();
	}
}

/**
 * enregistrement d'un profil
 */
function enreg($lesdonnees){
	try{
		// récupération des données en post
		$donnee = json_decode($lesdonnees);
		$datemesure = $donnee[0];
		$poids = $donnee[1];
		$taille = $donnee[2];
		$age = $donnee[3];
		$sexe = $donnee[4];
		// insertion dans la BD
		$cnx = connexionPDO();
		$larequete = "insert into profil (datemesure, poids, taille, age, sexe)";
		$larequete .= " values (\"$datemesure\", $poids, $taille, $age, $sexe)";
		$req = $cnx->prepare($larequete);
		$req->execute();
		return ("enreg%".$larequete);
	}catch(Exception $e){
		return "*************************************************Erreur !%".$e->getMessage();
	}
}

/**
 * suppression d'un profil
 */
function suppr($lesdonnees){
	try{
		// récupération des données en post
		$donnee = json_decode($lesdonnees);
		$datemesure = $donnee[0];
		// suppression dans la BD
		$cnx = connexionPDO();
		$larequete = "delete from profil where datemesure=\"$datemesure\"";
		$req = $cnx->prepare($larequete);
		$req->execute();
		return ("suppr%".$larequete);
	}catch(Exception $e){
		return "*************************************Erreur !%".$e->getMessage();
	}
}

?>