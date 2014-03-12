<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/header.php'; ?>

<style type="text/css">
	#connexion {width: 250px;
		color: #333;
		font-family: sans-serif;
		text-align: center;
		border: solid white 1px;
		border-radius: 5px;
		box-shadow: 1px 0px 5px white;
		background-color: white;
		display: inline-block;
		margin-left: 40%;
		margin-top: 10%;
		padding: 50px;
		background-color: #E5E5E5;

	}
	#connexion a {text-decoration: none;color: #333;font-weight: bold}
	#connexion img {margin-bottom: 20px;}
	#connexion input {margin-bottom: 20px;}
	#connexion input[type="submit"] {padding: 10px;font-weight: bold;font-size: 14px;}
	#connexion label {font-weight: bold;}
</style>

<div id="connexion">
	<img src="res/img/logo1.png">
	<form method="post" action="/BuntanV1/services/user/connect.php">
		<label>Login : </label><input type="text" name="login"/>
		<label>Password : </label><input type="password" name="mdp"/>
		<input type="submit" value="Connexion"/> ou <a href="/BuntanV1/fragments/user/inscription.php">inscription</a>
	</form>
	<?php if (isset($_POST['login'])) {
		echo "Vous n'etes pas connecte.";
	} ?>
</div>
</body>
</html>