<?php
	try{
		$bdd = new PDO('mysql:host=localhost;dbname=where_to_eat', 'root', '');
		$idregion = $_POST['region'];
		$requet = 'select departement from departement where id_region='.$idregion;
		$regionreq = $bdd->query($requet);
		$region = $regionreq->fetchAll(PDO::FETCH_COLUMN, 0);
		$region = json_encode($region, true);
		
			echo $region;
		
	}
	catch (PDOException $e) {
		print "Erreur !: " . $e->getMessage() . "<br/>";
		die();
	}

?>