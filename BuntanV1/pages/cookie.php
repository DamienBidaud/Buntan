<?php

  // Create Cookie.
  $value = 'My First Cookie ';
  setcookie("TestCookie", $value, time()+7200, "/pages/", "10.66.125.90");

	print_r($_COOKIE);

  // Read Cookies sent by the client.
  foreach($_COOKIE as $key => $value) {
	echo htmlspecialchars($key." - ".$value)."<br/>";
  }


?>