<?php

include 'conexion.php';
//$codigo = $_GET['codigo'];
//$nombrePizza = $_GET['pizza'];

//$consulta = "select * from pizzas where nombrePizza = '$nombrePizza'";
$consulta = "select * from pizzas";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
$producto[] = array_map('utf8_encode', $fila);
}

echo json_encode($producto);
$resultado -> close();

?>