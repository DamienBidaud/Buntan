<?php

session_start();

if (empty($_SESSION['count'])) {
	$_SESSION['count'] = 1; } else {
if ($_SESSION['count'] < 10){ $_SESSION['count']++;
?>
<p>Bonjour, vous avez vu cette page <?php echo $_SESSION['count']; ?> fois.</p>
<?php } else {
    session_destroy();
  ?>  <p>Nombre maximum de page atteint</p>  <?php
  }
}

?>