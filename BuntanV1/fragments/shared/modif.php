<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/menu.php'; ?>

<?php
	$result="";
	if(isset($_GET['idR'])){
		$id= $_GET['idR'];
		if(!empty($_GET['name'])){
			$name= $_GET['name'];
			$querry = $db->prepare("UPDATE user SET name=:name
				WHERE iduser=:id
				");
			$querry->execute(array(':id' => $id, ':name' => $name))or die(print_r($querry->errorInfo()));
		}
		if(!empty($_GET['mdp'])){
			$mdp= $_GET['mdp'];
			$querry = $db->prepare("UPDATE user SET password=:mdp
				WHERE iduser=:id
				");
			$querry->execute(array(':id' => $id, ':mdp' => $mdp))or die(print_r($querry->errorInfo()));
		}
		if(!empty($_GET['mail'])){
			$mail= $_GET['mail'];
			$querry = $db->prepare("UPDATE user SET mail_address=:mail
				WHERE iduser=:id
				");
			$querry->execute(array(':id' => $id, ':mail' => $mail))or die(print_r($querry->errorInfo()));
		}
	}

	header('Location: /BuntanV1/fragments/user/acceuil.php');

?>


<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/footer.php'; ?>