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

$jsDependencies[] = '/BuntanV1/pages/js/component/menu.js';
$jsDependencies[] = '/BuntanV1/pages/js/component/form.js';
$jsDependencies[] = '/BuntanV1/pages/js/view/workspace.js';

?>

<?php

include ($_SERVER['DOCUMENT_ROOT'] . '/BuntanV1/fragments/shared/footer.php');

?>

