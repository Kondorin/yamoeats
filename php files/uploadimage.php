<?php

$imagen = $_POST['foto'];
$nombre = $_POST['nombre'];


$path = "imagesusuarios/$nombre.png";

file_put_contents($path, base64_decode($imagen));

echo"SE SUBIO EXITOSAMENTE";


?>