<?php
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
            print "Erreur de connexion PDO :".$e;
            die();
        }
    }
