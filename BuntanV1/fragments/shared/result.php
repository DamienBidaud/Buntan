<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/menu.php'; ?>
<style type="text/css">
	ul {cursor: pointer;}
	li {list-style-type: none;}
	li #titre {font-weight: bold;}
</style>
<?php
	try{
		$result = "";

		if(!empty($_POST['login'])){
			?><h3>Resultat de la recherche :</h3><?php
			$requet = 'select iduser, name, mail_address from user where name LIKE "'.$_POST['login'].'" OR mail_address LIKE "'.$_POST['mail'].'"';
			$regionreq = $db->prepare($requet);
			$regionreq->execute();
			if(!empty($regionreq)){
				$result = $regionreq->fetch(PDO::FETCH_ASSOC);
				while(!empty($result)){ 
					echo '<a href="/BuntanV1/fragments/shared/utilisateur.php?idR='.$result['iduser'].'"><ul id="liste">';
						echo '<li><span id="titre">Nom: </span>'.$result['name'].'</li>';
						echo '<li><span id="titre">Adresse mail: </span>'.$result['mail_address'].'</li>';
						echo '<li>-----------------------------------------</li><br/>';
					echo '</ul>';
					$result = $regionreq->fetch(PDO::FETCH_ASSOC);
				}
			}
		}
		else{
			?><h3>Resultat de la recherche :</h3><?php
			$requet = 'select iduser, name, mail_address from user';
			$regionreq = $db->prepare($requet);
			$regionreq->execute();
			if(!empty($regionreq)){
				$result = $regionreq->fetch(PDO::FETCH_ASSOC);
				while(!empty($result)){ 
					echo '<a href="/BuntanV1/fragments/shared/utilisateur.php?idR='.$result['iduser'].'"><ul id="liste">';
						echo '<li><span id="titre">Nom: </span>'.$result['name'].'</li>';
						echo '<li><span id="titre">Adresse mail: </span>'.$result['mail_address'].'</li>';
						echo '<li>-----------------------------------------</li><br/>';
					echo '</ul></a>';
					$result = $regionreq->fetch(PDO::FETCH_ASSOC);
				}
			}
		}
		?>
		<?php
	}
	catch (PDOException $e) {
		print "Erreur !: " . $e->getMessage() . "<br/>";
		die();
	}


	include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/footer.php';
?>