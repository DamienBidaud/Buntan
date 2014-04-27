<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/menu.php'; ?>

<table class="layout">
<tr><td style="width:300px;">
	<div id="formulaire"></div>
</td><td>
	<div id="workspace"></div>
</td></tr>
</table>


<style>
	#mod {border: solid black 1px;padding: 30px;padding-bottom: 60px;}
	#mod p {margin-left: 300px;}
	h3 {margin-left: 300px;}
	#modif {width: 500px;margin-top: 30px;margin-left: 300px;}
	#modif label {padding: 20px;}
	#modif input[type="submit"] {padding: 10px;margin-left: 20px;}
   .layout {width:100%;}
    .layout td {vertical-align: top}
   #formulaire {width:300px;}
   .mymenu {display:inline-block;}
   .mymenu li {cursor:pointer;border-radius: 4px; padding:2px;}
   .mymenu li:hover {background-color: #ECECEC}
   #workspace {}
</style>

<?php
	try{
		$bdd = new PDO('mysql:host=localhost;dbname=buntan', 'root', '');
		$result = "";
		$requete = 'select iduser, name, mail_address from user where iduser LIKE "'.$_POST['nom'].'" OR name LIKE "'.$_POST['nom'].'"';
		$regionreq = $bdd->prepare($requete);
		$regionreq->execute();
		if(!empty($regionreq)){
			$result = $regionreq->fetch(PDO::FETCH_ASSOC);
			while(!empty($result)){ 
				echo '<ul id="liste">';
					echo '<li><span id="titre">Nom: </span>'.$result['name'].'</li>';
					echo '<li><span id="titre">Adresse mail: </span>'.$result['mail_address'].'</li>';
					echo '<li>-----------------------------------------</li><br/>';
				echo '</ul>';
				$result = $regionreq->fetch(PDO::FETCH_ASSOC);
			}
		}
	}
	catch (PDOException $e) {
		print "Erreur !: " . $e->getMessage() . "<br/>";
		die();
	}

?>


<div id="mod">
<h3>Modifier vos informations :</h3>
<p>Modifier les informations de votre compte : mot de passe et adresse mail</p>
<form method="post" action="/BuntanV1/services/user/update.php" id="modif">
	<label>Changer de mot de passe</label><input type="texte" name="mdp"/></br>
	<label>Confirmer le mot de passe</label><input type="texte" name="mdp2"/></br>
	<label>Modifier le mail</label><input type="texte" name="mail"/></br>
	<input type="submit" name="valider" value="Modifier"/>
</form>
</div>

<?php

include ($_SERVER['DOCUMENT_ROOT'] . '/BuntanV1/fragments/shared/footer.php');

?>