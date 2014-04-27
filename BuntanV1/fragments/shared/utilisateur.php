<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/menu.php'; ?>
<style type="text/css">
	#titre {
		font-weight: bold;
	}
	ul {
		list-style-type: none;
	}
</style> 
<?php
$result = "";
	if(isset($_GET['idR'])){
		$id= $_GET['idR'];
		$querry = $db->prepare("SELECT * from user 
				where iduser=:id
				");
		
		$querry->execute(array(':id' => $id))or die(print_r($querry->errorInfo()));
		$result = $querry->fetch(PDO::FETCH_ASSOC);
		if(!empty($result)){
		        do{ ?>
		        	<h3><?= $result['name'] ?></h3>
		        	<p><span id="titre">Mot de passe : </span><?= $result['password'] ?></p>
		        	<p><span id="titre">Adresse e-mail : </span><?= $result['mail_address'] ?></p>
			        <form method="GET" action="modif.php">
			        	<ul>
			        		<?php echo '<li><input type="hidden" name="idR" value="'.$id.'">'; ?>
			        		<li>Modifier le nom : <input type="text" name="name"/></li>
			        		<li>Modifier le mot de passe : <input type="text" name="mdp"/></li>
			        		<li>Modifier l'adresse mail : <input type="text" name="mail"/></li>
			        		<li><input type="submit" value="Modifier"/></li>
			        	
					</form>
					<form method="GET" action="suppr.php">
						<?php echo '<li><input type="hidden" name="idR" value="'.$id.'">'; ?>
						<li><input type="submit" value="Supprimer"/></li>
						</ul>
					</form>
		            <?php
		        }while ($result = $querry->fetch(PDO::FETCH_ASSOC));
		}
	}

include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/footer.php'; ?>