<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/header.php'; ?>

<style type="text/css">
	#insc-content {width: 400px;
		color: #333;
		font-family: sans-serif;
		text-align: center;
		border: solid white 1px;
		border-radius: 5px;
		box-shadow: 1px 0px 5px white;
		background-color: #E5E5E5;
		display: inline-block;
		margin-left: 37%;
		margin-top: 10%;
		padding: 50px;


	}
	#insc-content li {list-style-type: none;}
	#insc-content a {text-decoration: none;color: #333;font-weight: bold}
	#insc-content img {margin-bottom: 20px;}
	#insc-content input {margin-bottom: 20px;}
	#insc-content input[type="submit"] {padding: 10px;font-weight: bold;font-size: 14px;}
	#insc-content label {font-weight: bold;}
</style>

<div id="insc-content">
	<img src="/BuntanV1/res/img/logo1.png">
	<h3>Formulaire d'inscription : </h3>

	<form method="post" action="/BuntanV1/services/user/subscrib.php" id="inscription">
		<ul>
		<li><label>Nom d'utilisateur : </label><input type="text" name="login"/></li>
		<li><label>Mot de passe : </label><input type="text" name="mdp"/></li>
		<li><label>Adresse mail : </label><input type="text" name="mail"/></li>
		<li><a href="/BuntanV1"><input type="submit" name="valider" value="S'inscrire"/></a> ou <a href="/BuntanV1"> Se connecter</a></li>
		</ul>
	</form>
</div>

</body>
</html>

