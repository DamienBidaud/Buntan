<?php
include ($_SERVER['DOCUMENT_ROOT'] . '/BuntanV1/config.php');

//header("Location: ".$_SERVER['HTTP_REFERER']);

$login = $_POST['login'];
$mdp = $_POST['mdp'];

$db = new PDO('mysql:host=localhost;dbname=buntan', 'root', '');
$requete = $db->prepare("SELECT * FROM `buntan`.`user` WHERE name LIKE '".$login."' AND password LIKE '".$mdp."';") ;
$requete->execute();
$result = $requete->fetch(PDO::FETCH_ASSOC);
print_r($result);



    if(isset($result['name'])){
		$_SESSION['user_id'] = $login;
		header("Location: /BuntanV1/fragments/user/acceuil.php");
	}
	else {
		header("Location: /BuntanV1/fragments/user/inscription.php");
	}


?>