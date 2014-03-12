<style>
#login-form { color:white;position:absolute; background-color:#333; bottom:59px;right:0;padding:8px;opacity:0.9;}
#login-form label {color:white;}
#login-form a {color:white;padding:10px;}
#login-form #texte {display:inline-block;padding-right:15px;font-weight: bold;}
#login-form input {border-radius:3px;opacity: 0.7;}
#login-form input:hover {border-radius:3px;opacity: 1;}
</style>

<?php
session_start();
if ( !isset($_SESSION['user_id'] ) ) { 
?>
	<div id="login-form"> 
		<form method="POST" action="/BuntanV1/services/user/connect.php">
			<label>Login : </label><input type="text" name="login"/> - 
			<label>Mot de passe : </label><input type="password" name="mdp"/>
			<input type="submit" value="Connexion"/>
		</form>
	</div>
<?php  }  else { ?>
	<div id="login-form"><div id="texte">Bienvenue <?php echo $_SESSION['user_id']; ?> </div>-<a href="/BuntanV1/pages/admin/index.php">Mon compte</a> - <a href="/BuntanV1/services/user/logout.php">Se déconnecter</a></div>
<?php } ?>
