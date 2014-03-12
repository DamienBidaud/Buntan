			</div>
			<div id="footer">
				<ul>
				  <li>Buntan : Site et application réalisés par Thibault Dassonvillé, Damien Bidaud et Guillaume Blondeau.</li>
				</ul>
			</div>
		</div>

	</body>
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript">
		document.getElementById('liste').onclick=function(){
			var xhr = new XMLHttpRequest();
			//confirm("Voulez-vous supprimer l'utilisateur ?");
			nom = prompt('Modifier le nom :');
			xhr.open('POST', '/BuntanV1/services/user/modif.php');
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send('nom='+nom);

			//mail = prompt('Modifier le mail :');
			//alert(mail);
		}
	</script>
	<script>
	$( function() {
		$( 'audio' ).audioPlayer({
		   classPrefix: 'audioplayer',
		   strPlay: 'Play',
		   strPause: 'Pause',
		   strVolume: 'Volume'
		});
	});
	</script>
</html>