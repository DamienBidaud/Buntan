<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/menu.php';

	if(isset($_POST['mdp'])){
		$id= $_SESSION['user_id'];
		if(!empty($_POST['mdp'])){
			$mdp= $_POST['mdp'];
			$querry = $db->prepare("UPDATE user SET password=:mdp
				WHERE name=:id
				");
			$querry->execute(array(':id' => $id, ':mdp' => $mdp))or die(print_r($querry->errorInfo()));
		}
		if(!empty($_POST['mail'])){
			$mail= $_POST['mail'];
			$querry = $db->prepare("UPDATE user SET mail_address=:mail
				WHERE name=:id
				");
			$querry->execute(array(':id' => $id, ':mail' => $mail))or die(print_r($querry->errorInfo()));
		}
	}

	header('Location: /BuntanV1/fragments/user/acceuil.php');

?>
