<?php
/**
 * Classe de connexion et d'exécution des requêtes dans une BDD MySQL
 */
class ConnexionPDO {

	private $conn = null;

	/**
	 * constructeur privé : connexion à la BDD
	 * @param string $login 
	 * @param string $mdp
	 * @param string $bd
	 * @param string $serveur
	 * @param int $port
	 */
    public function __construct($login, $mdp, $bd, $serveur, $port){
        try {
            $this->conn = new PDO("mysql:host=$serveur;dbname=$bd;port=$port", $login, $mdp);
			$this->conn->query('SET CHARACTER SET utf8');
        } catch (PDOException $e) {
            throw $e;
        }
    }

	/**
	 * Exécution d'une requête de mise à jour (insert, update, delete)
	 * @param string $requete
	 * @param array $param
	 * @return résultat requête (booléen)
	 */
	public function execute($requete, $param=null){
		try{	
			$requetePrepare = $this->conn->prepare($requete);
			if($param != null){
				foreach($param as $key => &$value){				
					$requetePrepare->bindParam(":$key", $value);				
				}
			}	
			$requetePrepare->execute();			
			return ($requetePrepare->rowCount() != 0);
		}catch(Exception $e){
			return null;
		}
	}

	/**
	 * Exécution d'une requête select retournant plusieurs lignes
	 * @param string $requete
	 * @param array $param
	 * @return lignes récupérées
	 */
	public function queryAll($requete, $param=null){
		try{
			$requetePrepare = $this->conn->prepare($requete);
			if($param != null){
				foreach($param as $key => &$value){
					$requetePrepare->bindParam(":$key", $value);
				}
			}
			$requetePrepare->execute();				
			$result = $requetePrepare->fetchAll(PDO::FETCH_ASSOC);
			if($requetePrepare->rowCount() != 0){
				return $result;
			}else{
				return null;
			}
		}catch(Exception $e){
			return null;
		}		
	}

	/**
	 * Exécution d'une requête select retournant une ligne
	 * @param string $requete
	 * @param array $param
	 * @return ligne récupérée
	 */
	public function query($requete, $param){
		try{
			$requetePrepare = $this->conn->prepare($requete);
			if($param != null){
				foreach($param as $key => &$value){
					$requetePrepare->bindParam(":$key", $value);
				}
			}
			$requetePrepare->execute();	
			$result = $requetePrepare->fetch(PDO::FETCH_ASSOC);
			if($requetePrepare->rowCount() != 0){
				return $result;
			}else{
				return null;
			}
		}catch(Exception $e){
			return null;
		}		
	}

}