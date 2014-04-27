<?php
try
{
    $db = new PDO('mysql:host=localhost;dbname=buntan', 'root', '');
    $db->exec("SET CHARACTER SET utf8");
}
catch (Exception $e)
{
    die('Erreur : ' . $e->getMessage());
}
?>
