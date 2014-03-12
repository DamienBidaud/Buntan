<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/menu.php'; ?>
	
	<fieldset class="box">
	<legend>Liste des musiques</legend>
		<table id="list">
			<?php
			
			$dirname = $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/musique';
			$dir = opendir($dirname);

			while($file = readdir($dir)) {
				if($file != '.' && $file != '..' && !is_dir($dirname.$file))
				{
					echo '<tr><td>'.$file.'</td></tr>';
				}
			}

			closedir($dir);
			
			?>
		</table>
	</fieldset>

	<fieldset class="box">
	<legend>Lecteur</legend>
		<div class="lecteur">
			<div id="progressbar"></div>
			<p id="titlePlaying">21 - Inner Light.mp3</p>
			<audio controls="controls" id="player" preload="auto">
			  <source src="/BuntanV1/musique/21 - Inner Light.mp3" type="audio/mp3" />
			  Desole, mais votre navigateur ne gere pas le player !
			</audio>
			<table id="tabControls">
				<tr>
					<td><p class="ui-state-default ui-corner-all" id="timePlaying" style="width:84px;">...</p></td>
					<!--<td><div id="controls">
						<img id="previous" src="img/previous.png"> <img id="play" src="img/play.png"> <img id="next" src="img/next.png">
					</div></td>-->
					<td><div id="volume"></div></td>
				</tr>
			</table>
		</div>
	</fieldset>
	


<?php include $_SERVER['DOCUMENT_ROOT'].'/BuntanV1/fragments/shared/footer.php'; ?>