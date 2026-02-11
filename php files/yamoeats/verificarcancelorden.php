<?php

include 'conexion.php';
$usuario = $_GET['usuario'];

$consulta = "select * from pedidos where status = 'pendiente' and usuario = '$usuario'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
$producto[] = array_map('utf8_encode', $fila);
}

echo json_encode($producto);
$resultado -> close();

?>