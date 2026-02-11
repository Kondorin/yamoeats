<?php

include 'conexion.php';
$nombrePizza = $_GET['pizza'];

$consulta = "select * from pizzas where nombrePizza = '$nombrePizza'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
$producto[] = array_map('utf8_encode', $fila);
}

echo json_encode($producto);
$resultado -> close();

?>