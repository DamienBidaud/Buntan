<?php
session_start();
if(isset($_POST['mdp'])) {
	try{
		$mdp = $_POST['mdp'];
		$mail = $_POST['mail'];
		$db = new PDO('mysql:host=localhost;dbname=buntan', 'root', '');
		$id = $db->prepare("SELECT `user`.`iduser` FROM `buntan`.`user` WHERE  `user`.`name` LIKE '".$_SESSION['user_id']."'");
		$id->execute();
		$result = $id->fetch(PDO::FETCH_ASSOC);
		$requete = "UPDATE  `buntan`.`user` SET  `password` =  '".$mdp."', `mail_address` =  '".$mail."' WHERE  `user`.`iduser` =".$result['iduser'].";" ;
		$id = $db->prepare($requete);
		$id->execute();
	    $db = null;
	    print_r($result);

	}catch (PDOException $e) {
	    print "Erreur !: " . $e->getMessage() . "<br/>";
	    die();
	}
}

header('Location: /BuntanV1/services/user/logout.php');
?>