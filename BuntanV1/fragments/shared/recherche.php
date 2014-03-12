<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/menu.php'; ?>

<style type="text/css">
	#recherche {border: solid black 1px;}
	#recherche h3 {padding-left: 200px;padding-top: 30px;}
	#recherche p {padding-left: 200px;}
	#recherche table {padding: 10px;padding-left: 200px;}
	#recherche input[type=submit] {padding: 10px;padding-left: 20px;padding-right: 20px;margin-top: 10px;}
</style>
<div id="recherche">
	<h3>Fonction de recherche :</h3>
	<p>Chercher des utilisateurs par leur nom ou leur mail.</p>
	<p>Si vous n'entrez rien, tous les utilisateurs seront affichés.</p>
	<form method=POST action="result.php">
		<table>
			<tr>
				<td>Nom : </td>
				<td><input type="text" name="login" /></td>
			</tr>
			<tr>
				<td>Mail : </td>
				<td><input type="text" name="mail" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Rechercher" /></td>
			</tr>
		</table>
	</form>
</div>
<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/footer.php'; ?>