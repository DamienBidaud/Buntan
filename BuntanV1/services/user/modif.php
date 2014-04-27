<?php include $SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/menu.php';

session_start();
if(isset($_POST['nom'])) {
	try{
		$nom = $_POST['nom'];
		$mdp = $_POST['mdp'];
		$db = new PDO('mysql:host=localhost;dbname=buntan', 'root', '');
		//$id = $db->prepare("SELECT `user`.`iduser` FROM `buntan`.`user` WHERE  `user`.`name` LIKE '".$_SESSION['user_id']."'");
		//$id->execute();
		$result = $id->fetch(PDO::FETCH_ASSOC);
		$requete = "UPDATE  user SET  mail_address =  '".$mail."' WHERE  iduser =".$result['iduser'].";" ;
		$id = $db->prepare($requete);
		$id->execute();
	    $db = null;
	    print_r($result);

	}catch (PDOException $e) {
	    print "Erreur !: " . $e->getMessage() . "<br/>";
	    die();
	}
}

include $SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/footer.php'; ?>