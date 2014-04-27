<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/header.php'; 
include $_SERVER['DOCUMENT_ROOT'] . '/BuntanV1/services/database/database.php';
?>

	<div id="page">
		<div id="content-header">
			<a id="logo" href="/BuntanV1/"><img src='/BuntanV1/res/img/miniLogo.png'/></a>
			
			<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/user/connection.php'; ?>

			<ul id="main-menu">
				<li><a href="/BuntanV1/fragments/user/acceuil.php">Acceuil</a></li>
				<li><a href="/BuntanV1/fragments/shared/recherche.php">Rechercher</a></li>
				<li><a href="/BuntanV1/fragments/user/musique.php">Musique</a></li>
				<li><a href="/BuntanV1/fragments/user/musique.php">Vidéo</a></li>
				<li><a href="http://www.youtube.com/?gl=FR&hl=fr">Youtube</a></li>
			</ul>
		</div>
		<div id="content">
