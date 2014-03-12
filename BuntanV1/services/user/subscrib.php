<?php
if(isset($_POST['login'])) {
	try{
		$login = $_POST['login'];
		$mdp = $_POST['mdp'];
		$mail = $_POST['mail'];
		$db = new PDO('mysql:host=localhost;dbname=buntan', 'root', '');
		$requete = $db->prepare("INSERT INTO `buntan`.`user` (`iduser`, `password`, `name`, `mail_address`) VALUES (NULL, '".$mdp."', '".$login."', '".$mail."');") ;
		$requete->execute();
	    $db = null;

	}catch (PDOException $e) {
	    print "Erreur !: " . $e->getMessage() . "<br/>";
	    die();
	}
}

header("Location: /BuntanV1");
?>