<?php
include "fonctions.php";

// contrôle de réception de paramètre
if(isset($_REQUEST["operation"])){
	
	// demande de récupération du dernier profil
	if($_REQUEST["operation"]=="dernier"){
		
		try{
			print ("dernier%");
			$cnx = connexionPDO();
			$req = $cnx->prepare("select * from profil order by datemesure desc");
			$req->execute();
			// s'il y a un profil, récupération du premier
			if($ligne = $req->fetch(PDO::FETCH_ASSOC)){
				print(json_encode($ligne));
			}
		}catch(PDOException $e){
			print "Erreur !%".$e->getMessage();
			die();
		}
		
	// enregistrement nouveau profil	
	}elseif($_REQUEST["operation"]=="enreg"){

		try{
			// récupération des données en post
			$lesdonnees = $_REQUEST["lesdonnees"];
			$donnee = json_decode($lesdonnees);
			$datemesure = $donnee[0];
			$poids = $donnee[1];
			$taille = $donnee[2];
			$age = $donnee[3];
			$sexe = $donnee[4];
			// insertion dans la BD
			print ("enreg%");
			$cnx = connexionPDO();
			$larequete = "insert into profil (datemesure, poids, taille, age, sexe)";
			$larequete .= " values (\"$datemesure\", $poids, $taille, $age, $sexe)";
			print ($larequete);
			$req = $cnx->prepare($larequete);
			$req->execute();
		}catch(PDOException $e){
			print "Erreur !%".$e->getMessage();
			die();
		}
		
	// récupération de tous les profils	
	}elseif($_REQUEST["operation"]=="tous"){

		try{
			print ("tous%");
			$cnx = connexionPDO();
			$req = $cnx->prepare("select * from profil order by datemesure desc");
			$req->execute();
			while($ligne = $req->fetch(PDO::FETCH_ASSOC)){
				$resultat[]= $ligne;
			}
			print(json_encode($resultat));
		}catch(PDOException $e){
			print "Erreur !%".$e->getMessage();
			die();
		}
		
	// suppression d'un profil	
	}elseif($_REQUEST["operation"]=="suppr"){

		try{
			// récupération des données en post
			$lesdonnees = $_REQUEST["lesdonnees"];
			$donnee = json_decode($lesdonnees);
			$datemesure = $donnee[0];
			// suppression dans la BD
			print ("suppr%");
			$cnx = connexionPDO();
			$larequete = "delete from profil where datemesure=\"$datemesure\"";
			print ($larequete);
			$req = $cnx->prepare($larequete);
			$req->execute();
		}catch(PDOException $e){
			print "Erreur !%".$e->getMessage();
			die();
		}
		
	}
	
}