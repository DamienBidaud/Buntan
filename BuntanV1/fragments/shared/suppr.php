<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/menu.php'; ?>

<?php
	$result="";
	if(isset($_GET['idR'])){
		$id= $_GET['idR'];
		$querry = $db->prepare("DELETE FROM user
				WHERE iduser=:id
				");
		$querry->execute(array(':id' => $id))or die(print_r($querry->errorInfo()));
	}

	header('Location: /BuntanV1/fragments/user/acceuil.php');

?>


<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/footer.php'; ?>