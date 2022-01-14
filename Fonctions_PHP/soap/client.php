<?php

// première étape : désactiver le cache lors de la phase de test
ini_set("soap.wsdl_cache_enabled", "0");
ini_set('soap.wsdl_cache_ttl',0);
// lier le client au fichier WSDL
$clientSOAP = new SoapClient('coach.wsdl');

// executer la methode tous
    echo $clientSOAP->tous();
	echo"<br />";
	
	$param = "[\"2021-09-16 11:57:35\",50,166,22,0]";
	echo $clientSOAP->enreg($param);

?>