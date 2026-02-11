<?php


include 'conexion.php';
$email = $_GET['email'];

$consulta = "select * from usuarios where email = '$email'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
$producto[] = array_map('utf8_encode', $fila);
}

echo json_encode($producto);
$resultado -> close();

?>