<?php
include ($_SERVER['DOCUMENT_ROOT'] . '/BuntanV1/config.php');

session_destroy();

header("Location: /BuntanV1");

?>