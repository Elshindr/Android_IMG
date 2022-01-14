<?php
include_once("ConnexionPDO.php");

/**
 * Classe de construction des requêtes SQL à envoyer à la BDD
 */
class AccessBDD {
	
	public $login="root";
	public $mdp="";
	public $bd="coach";
	public $serveur="localhost";
	public $port="3306";	
	public $conn = null;
	
	/**
	 * constructeur : demande de connexion à la BDD
	 */
	public function __construct(){
		try{
			$this->conn = new ConnexionPDO($this->login, $this->mdp, $this->bd, $this->serveur, $this->port);
		}catch(Exception $e){
			throw $e;
		}
	}
	
	/**
	 * récupération de toutes les lignes d'une table
	 * @param string $table nom de la table
	 * @return lignes de la table
	 */
	public function selectAll($table){
		if($this->conn != null){
			return $this->conn->queryAll("select * from $table;");
		}else{
			return null;
		}
	}
	
	/**
	 * récupération d'une ligne d'une table
	 * @param string $table nom de la table
	 * @param string $id id (datemesure) de la ligne à récupérer
	 * @return ligne de la table correspondant à l'id
	 */	
	public function selectOne($table, $id){
		if($this->conn != null){
			$param = array(
				"id" => $id
			);
			return $this->conn->query("select * from $table where datemesure=:id;", $param);		
		}else{
			return null;
		}
	}
		
	/**
	 * suppresion d'une ligne dans une table
	 * @param string $table nom de la table
	 * @param string $id id (datemesure) de la ligne à supprimer
	 * @return true si la suppression a fonctionné
	 */	
	public function deleteOne($table, $id){
		if($this->conn != null){
			$param = array(
				"id" => $id
			);
			return $this->conn->execute("delete from $table where datemesure=:id;", $param);		
		}else{
			return null;
		}
	}

	/**
	 * ajout d'une ligne dans une table
	 * @param string $table nom de la table
	 * @param array $champs nom et valeur de chaque champs de la ligne
	 * @return true si l'ajout a fonctionné
	 */	
	public function insertOne($table, $champs){
		if($this->conn != null && $champs != null){
			// construction de la requête
			$requete = "insert into $table (";
			foreach ($champs as $key => $value){
				$requete .= "$key,";
			}
			// (enlève la dernière virgule)
			$requete = substr($requete, 0, strlen($requete)-1);
			$requete .= ") values (";
			foreach ($champs as $key => $value){
				$requete .= ":$key,";
			}
			// (enlève la dernière virgule)
			$requete = substr($requete, 0, strlen($requete)-1);
			$requete .= ");";	
			return $this->conn->execute($requete, $champs);		
		}else{
			return null;
		}
	}

	/**
	 * modification d'une ligne dans une table
	 * @param string $table nom de la table
	 * @param string $id id (datemesure) de la ligne à modifier
	 * @param array $param nom et valeur de chaque champs de la ligne
	 * @return true si la modification a fonctionné
	 */	
	public function updateOne($table, $id, $champs){
		if($this->conn != null && $champs != null){
			$champs["id"] = $id;
			// construction de la requête
			$requete = "update $table set ";
			foreach ($champs as $key => $value){
				$requete .= "$key=:$key,";
			}
			// (enlève la dernière virgule)
			$requete = substr($requete, 0, strlen($requete)-1);
			$requete .= " where datemesure=:id;";		
			return $this->conn->execute($requete, $champs);		
		}else{
			return null;
		}
	}

}